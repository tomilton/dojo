package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.oracle.DefaultCache;
import co.com.nequi.model.oracle.OracleService;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FreezeAccountUseCase {
    private final AccountService accountService;
    private final CustomerDefaultDataRepository defaultDataRepository;
    private final OracleService oracleService;

    public Mono<ResponseMdw> freezeAccount(RequestMdw requestMdw){
        FreezeAccountRQ freezeAccountRQ = (FreezeAccountRQ) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<DefaultCache> defaultCacheOracle = this.oracleService.getDefaultProperties();
        defaultCacheOracle.map(resp -> {
            if(resp == null){
                System.out.println("no existe default cache,se crea");
                Mono<DefaultCache> defaultCacheSave = this.oracleService.saveDefaultProperties();
                return defaultCacheSave;
            }
            return Mono.just(new DefaultCache("",""));
        }).subscribe((e) -> System.out.println("este es el valor de cache "+e.toString()));
        Mono<FreezeAccountRsService> responseFreezeAccount = this.accountService.freezeAccount(freezeAccountRQ);
        /**Mono.zip(responseFreezeAccount,defaultCacheOracle).flatMap((data) -> {
            data.getT1();
            data.getT2();
            return  Mono.just(new FreezeAccountRsService(Boolean.FALSE, ""));
        }).map((responseBuild) ->  ResponseUtil.buildResponseSuccess(responseBuild,requestMdw));**/
        return responseFreezeAccount
                .onErrorResume((e) ->  Mono.just(new FreezeAccountRsService(Boolean.FALSE, e.getMessage())) )
                .map((res)-> res.getStatus() ? Mono.just(new FreezeAccountRs("")) : Mono.just(new FreezeAccountRs(res.getMessage())))
                .map((responseService) -> ResponseUtil.buildResponseSuccess(responseService.block(),requestMdw));
    }
}
