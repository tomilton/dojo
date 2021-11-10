package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FreezeAccountUseCase {
    private final AccountService accountService;

    public Mono<ResponseMdw> freezeAccount(RequestMdw requestMdw){
        FreezeAccountRQ freezeAccountRQ = (FreezeAccountRQ) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<FreezeAccountRsService> responseFreezeAccount = this.accountService.freezeAccount(freezeAccountRQ);
        return responseFreezeAccount
                .onErrorResume((e) ->  Mono.just(new FreezeAccountRsService(Boolean.FALSE, e.getMessage())) )
                .map((res)-> res.getStatus() ? Mono.just(new FreezeAccountRs("")) : Mono.just(new FreezeAccountRs(res.getMessage())))
                .map((responseService) -> buildResponse(responseService.block(),requestMdw));
    }

    private ResponseMdw buildResponse(FreezeAccountRs freezeAccountRs,RequestMdw requestMdw){
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                Constant.COMMON_STRING_SUCCESS, "", "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, freezeAccountRs);
        ResponseMdw responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
        return responseMdw;
    }
}
