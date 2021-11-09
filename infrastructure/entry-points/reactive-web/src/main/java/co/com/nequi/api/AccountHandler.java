package co.com.nequi.api;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.trace.TraceAdapter;
import co.com.nequi.trace.builder.TraceBuilder;
import co.com.nequi.trace.dto.TraceDto;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@RequiredArgsConstructor
public class AccountHandler {
    private final FreezeAccountUseCase freezeUserCase;
    private final ObjectMapper mapper;
    @Autowired
    private final TraceAdapter traceAdapter;
    private String messageIdAct;

    public Mono<ServerResponse> freezeAccount(ServerRequest serverRequest){
        Mono<RequestJsonMdw> freezeAccountRqDtoMono = serverRequest.bodyToMono(RequestJsonMdw.class);
        return freezeAccountRqDtoMono
                .flatMap(requestMdw -> {
                    this.buildTrace(requestMdw,"IN");
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    FreezeAccountRqDto freezeAccountRQ = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), FreezeAccountRqDto.class);
                    mdw.getRequestHeaderOut().getBody().setAny(freezeAccountRQ.getFreezeAccountRQ());
                    return freezeUserCase.freezeAccount(mdw);
                })
                .map(object -> {
                    this.buildTrace(object,"OUT");
                    return object;
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/account/freezeAccount"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        //BodyInserters.fromResource(new ByteArrayResource(os.toByteArray()))
                        .body(fromObject(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }

    private void buildTrace(Object object,String type){
        traceAdapter.generate(new TraceBuilder().with(traceBuilder -> {
            traceBuilder.setOperation(type);
            traceBuilder.setPhone("TELEFONO");
            if(object instanceof ResponseMdw){
                ResponseMdw responseMdw = (ResponseMdw) object;
                traceBuilder.setResponse(responseMdw.toString());
            }
            if(object instanceof  RequestJsonMdw){
                RequestJsonMdw requestJsonMdw = (RequestJsonMdw) object;
                traceBuilder.setRegion(requestJsonMdw.getRequestHeaderOut().getHeader().getMessageContext().getProperty().getItem().stream().filter(pred -> pred.getKey().equals("Region")).findFirst().get().getValue());
                traceBuilder.setRequest(requestJsonMdw.toString());
                this.messageIdAct = requestJsonMdw.getRequestHeaderOut().getHeader().getMessageID();
            }
            traceBuilder.setMessageId(this.messageIdAct);
            traceBuilder.setService(this.getClass().getName());
        }).createTrace());
    }


}
