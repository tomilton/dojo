package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FreezeAccountUseCase {
    private final AccountService accountService;
    private final CustomerDefaultDataRepository defaultDataRepository;
    private Integer bankIdDefaultData = Integer.valueOf("157");
    private String servicioId ="3";

    public Mono<ResponseMdw> freezeAccount(RequestMdw requestMdw){
        FreezeAccountRQ freezeAccountRQ = (FreezeAccountRQ) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<CustomerDefaultData> defaultDataBank = this.defaultDataRepository.getDefaultDataId(bankIdDefaultData,servicioId);
        return defaultDataBank.flatMap(defaultData -> this.accountService.freezeAccount(freezeAccountRQ,defaultData.getValorDefecto()))
                .onErrorResume((e) ->  Mono.just(new FreezeAccountRsService(Boolean.FALSE, e.getMessage())) )
                .map((res)-> res.getStatus() ? Mono.just(new FreezeAccountRs("")) : Mono.just(new FreezeAccountRs(res.getMessage())))
                .map((responseService) -> ResponseUtil.buildResponseSuccess(responseService.block(),requestMdw));
    }
}
