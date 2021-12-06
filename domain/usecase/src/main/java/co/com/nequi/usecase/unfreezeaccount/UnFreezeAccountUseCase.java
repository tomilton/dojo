package co.com.nequi.usecase.unfreezeaccount;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UnFreezeAccountUseCase {
    private final AccountService accountService;
    private final CustomerDefaultDataRepository defaultDataRepository;
    private Integer bankIdDefaultData = Integer.valueOf("157");
    private String servicioId ="3";

    public Mono<ResponseMdw> unFreezeAccount(RequestMdw requestMdw){
        UnFreezeAccountRq unFreezeAccountRq = (UnFreezeAccountRq) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<CustomerDefaultData> defaultDataBank = this.defaultDataRepository.getDefaultDataId(bankIdDefaultData,servicioId);
        return defaultDataBank.flatMap(defaultData -> {
                    unFreezeAccountRq.setBankId(defaultData.getValorDefecto());
                    return this.accountService.unFreezeAccount(unFreezeAccountRq);
                })
                //.onErrorResume((e) ->  Mono.just(new UnFreezeAccountRsService(e.getMessage())) )
                .map((res)-> Mono.just(new UnFreezeAccountBrokerRS(res.getMessage())) )
                .map((responseService) -> ResponseUtil.buildResponseSuccess(responseService.block(),requestMdw))
                .onErrorResume(error -> ResponseUtil.handleErrors(error, requestMdw));
    }
}
