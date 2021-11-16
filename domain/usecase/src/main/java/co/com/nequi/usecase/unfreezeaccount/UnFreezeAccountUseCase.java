package co.com.nequi.usecase.unfreezeaccount;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UnFreezeAccountUseCase {
    private final AccountService accountService;

    public Mono<ResponseMdw> unFreezeAccount(RequestMdw requestMdw){
        UnFreezeAccountRq unFreezeAccountRq = (UnFreezeAccountRq) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<UnFreezeAccountRsService> responseFreezeAccount = this.accountService.unFreezeAccount(unFreezeAccountRq);
        return responseFreezeAccount
                .onErrorResume((e) ->  Mono.just(new UnFreezeAccountRsService(e.getMessage())) )
                .map((res)-> Mono.just(new UnFreezeAccountBrokerRS(res.getMessage())) )
                .map((responseService) -> ResponseUtil.buildResponseSuccess(responseService.block(),requestMdw));
    }
}
