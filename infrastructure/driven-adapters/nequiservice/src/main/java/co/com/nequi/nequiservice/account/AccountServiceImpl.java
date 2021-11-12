package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.model.exceptions.AccountOperationException;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public  WebClient webClient;

    @Override
    public Mono<FreezeAccountRsService> freezeAccount(FreezeAccountRQ freezeAccountRQ) {
        Mono<FinacleResponse> finacleResponse = webClient.post()
                .uri("/V1/banks/1/savings/FreezeAccount")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(freezeAccountRQ)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> {
                            System.out.println("on status "+ resp);
                            return Mono.error(new AccountOperationException("Error comunicacion finacle"));
                        }).bodyToMono(FinacleResponse.class)
                .onErrorMap(throwable -> new AccountOperationException(throwable.getMessage()));

        return finacleResponse.map(response -> {
            FreezeAccountRs freezeAccountRs = (FreezeAccountRs) response.getData();
            if(freezeAccountRs.getFreezeAccountRsCustomData() != null &&
                    freezeAccountRs.getFreezeAccountRsCustomData().getData().getStatus().equals("SUCCESS")){
                return new FreezeAccountRsService(Boolean.TRUE,"");
            }else{
                return new FreezeAccountRsService(Boolean.FALSE,freezeAccountRs.getFreezeAccountRsCustomData().getData().getMessage());
            }
        });
    }

    @Override
    public Mono<UnFreezeAccountRsService> unFreezeAccount(UnFreezeAccountRq unFreezeAccountRq) {
       Mono<FinacleResponse> finacleResponse = webClient.method(HttpMethod.DELETE)
                .uri("V1.0/banks/"+unFreezeAccountRq.getBankId()+"/savings/accounts/"+unFreezeAccountRq.getAccountId()+"/freeze")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(unFreezeAccountRq)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new AccountOperationException("Error comunicacion finacle")))
                .bodyToMono(FinacleResponse.class)
                .onErrorMap(throwable -> new AccountOperationException(throwable.getMessage()));
        return finacleResponse.map(response -> {
            UnFreezeAccountRs unFreezeAccountRs = (UnFreezeAccountRs) response.getData();
            return new UnFreezeAccountRsService(unFreezeAccountRs.getData());
        });
    }
}
