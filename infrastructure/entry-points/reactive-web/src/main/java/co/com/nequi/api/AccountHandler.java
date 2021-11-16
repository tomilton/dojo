package co.com.nequi.api;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
import co.com.nequi.usecase.unfreezeaccount.UnFreezeAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static co.com.nequi.api.util.UriConstants.URI_CREATED_FREEZE_ACCOUNT;
import static co.com.nequi.api.util.UriConstants.URI_CREATED_UNFREEZE_ACCOUNT;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@RequiredArgsConstructor
public class AccountHandler {
    private final FreezeAccountUseCase freezeUserCase;
    private final UnFreezeAccountUseCase unFreezeAccountUseCase;
    private final ObjectMapper mapper;

    public Mono<ServerResponse> freezeAccount(ServerRequest serverRequest){
        Mono<RequestJsonMdw> freezeAccountRqDtoMono = serverRequest.bodyToMono(RequestJsonMdw.class);
        return freezeAccountRqDtoMono
                .flatMap(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    FreezeAccountRqDto freezeAccountRQ = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), FreezeAccountRqDto.class);
                    mdw.getRequestHeaderOut().getBody().setAny(freezeAccountRQ.getFreezeAccountRQ());
                    return freezeUserCase.freezeAccount(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create(URI_CREATED_FREEZE_ACCOUNT))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }

    public Mono<ServerResponse> unFreezeAccount(ServerRequest serverRequest){
        Mono<RequestJsonMdw> unfreezeAccountRqDtoMono = serverRequest.bodyToMono(RequestJsonMdw.class);
        return unfreezeAccountRqDtoMono
                .flatMap(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    UnFreezeAccountBrokerRQMock unFreezeAccountBrokerRQ = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), UnFreezeAccountBrokerRQMock.class);
                    UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = UnFreezeAccountRqCustomData.builder().freezeReasonCode(unFreezeAccountBrokerRQ.getUnfreezeAccountBrokerRQ().getReasonCode()).build();
                    unFreezeAccountRqCustomData.setFreezeReasonCode(unFreezeAccountBrokerRQ.getUnfreezeAccountBrokerRQ().getReasonCode());
                    UnFreezeAccountRq freezeAccountRQ = UnFreezeAccountRq.builder().accountId(unFreezeAccountBrokerRQ.getUnfreezeAccountBrokerRQ().getAccountNumber()).bankId("1")
                            .accountUnFreezeRq_Customdata(unFreezeAccountRqCustomData).build();
                    mdw.getRequestHeaderOut().getBody().setAny(freezeAccountRQ);
                    return unFreezeAccountUseCase.unFreezeAccount(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create(URI_CREATED_UNFREEZE_ACCOUNT))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }


}
