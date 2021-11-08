package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.model.exceptions.AccountOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private WebClient webClient;

    @Override
    public Mono<FreezeAccountRsService> freezeAccount(FreezeAccountRqDto freezeAccountRqDto) {
        Mono<FinacleResponse> finacleResponse = webClient.post()
                .uri("/V1/banks/1/savings/FreezeAccount")
                .bodyValue(freezeAccountRqDto)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new AccountOperationException("Error comunicacion finacle")))
                .bodyToMono(FinacleResponse.class)
                .onErrorMap(throwable -> new AccountOperationException(throwable.getMessage()));

        return finacleResponse.map(response -> {
            if(response.getData()
                    .getFreezeAccountRsCustomData().getData().getStatus().equals("SUCCESS")){
                return new FreezeAccountRsService(Boolean.TRUE,"");
            }else{
                return new FreezeAccountRsService(Boolean.FALSE,response.getData()
                        .getFreezeAccountRsCustomData().getData().getMessage());
            }
        });
    }
}
