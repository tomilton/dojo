package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private WebClient webClient;

    @Override
    public Mono<Boolean> freezeAccount(FreezeAccountRqDto freezeAccountRqDto) {
        Mono<FinacleResponse> finacleResponse = webClient.post()
                .uri("/V1/banks/1/savings/FreezeAccount")
                .retrieve().bodyToMono(FinacleResponse.class);
        return finacleResponse.map((response -> response.getData()
                .getFreezeAccountRsCustomData().getData().getStatus().equals("SUCCESS")));
    }
}
