package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.nequiservice.account.dto.*;
import co.com.nequi.model.exceptions.AccountOperationException;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import static co.com.nequi.nequiservice.util.Constants.SUCCESS;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public  WebClient webClient;

    @Value("${finacle.uri.freezeAccount}")
    public String uriFreezeAccount;

    @Value("${finacle.uri.unfreezeAccount}")
    public String uriUnFreezeAccount;

    @Autowired
    public  ObjectMapper mapper;

    @Override
    public Mono<FreezeAccountRsService> freezeAccount(FreezeAccountRQ freezeAccountRQ) {
        Mono<FinacleResponse> finacleResponse = webClient.post()
                .uri(uriFreezeAccount,"1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(freezeAccountRQ)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> this.buildAccountOperationException())
                .bodyToMono(FinacleResponse.class)
                .onErrorMap(throwable -> new AccountOperationException(throwable.getMessage()));

        return finacleResponse.map(response -> {
            FreezeAccountRsCustomDataMock freezeAccountRs = mapper.map(response.getData(), FreezeAccountRsCustomDataMock.class);
            if(freezeAccountRs.getFreezeAccountRs_Customdata().getData() != null &&
                    freezeAccountRs.getFreezeAccountRs_Customdata().getData().getStatus().equals(SUCCESS)){
                return new FreezeAccountRsService(Boolean.TRUE,"");
            }else{
                return new FreezeAccountRsService(Boolean.FALSE,freezeAccountRs.getFreezeAccountRs_Customdata().getData().getMessage());
            }
        });/**.onErrorContinue(Exception.class, (ex, o) -> Mono.just(new FreezeAccountRsService(Boolean.FALSE,"error de mapeo")))*/
    }

    @Override
    public Mono<UnFreezeAccountRsService> unFreezeAccount(UnFreezeAccountRq unFreezeAccountRq) {
       Mono<FinacleResponse> finacleResponse = webClient.method(HttpMethod.DELETE)
                .uri(uriUnFreezeAccount,unFreezeAccountRq.getBankId(),unFreezeAccountRq.getAccountId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(unFreezeAccountRq)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> this.buildAccountOperationException())
                .bodyToMono(FinacleResponse.class)
                .onErrorMap(throwable -> new AccountOperationException(throwable.getMessage()));
        return finacleResponse.map(response -> {
            UnFreezeAccountRsMockData unFreezeAccountRs = mapper.map(response.getData(), UnFreezeAccountRsMockData.class);
            return new UnFreezeAccountRsService(unFreezeAccountRs.getData().getAccountUnFreezeRs().getData());
        });
    }

    private Mono<AccountOperationException> buildAccountOperationException(){
        return Mono.error(new AccountOperationException("Error comunicacion finacle"));
    }
}
