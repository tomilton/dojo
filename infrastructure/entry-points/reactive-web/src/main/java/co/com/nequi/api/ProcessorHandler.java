package co.com.nequi.api;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.processor.IprocessAdapter;
import co.com.nequi.model.requestmdw.RequestMdw;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static co.com.nequi.api.util.UriConstants.URI_CREATED_FREEZE_ACCOUNT;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class ProcessorHandler {
    private final IprocessAdapter processAdapter;
    private final ObjectMapper mapper;


    public Mono<ServerResponse> execute(ServerRequest serverRequest){
        Mono<RequestJsonMdw> requestProcessorMono = serverRequest.bodyToMono(RequestJsonMdw.class);
        return requestProcessorMono
                .flatMap(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    FreezeAccountRqDto freezeAccountRQ = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), FreezeAccountRqDto.class);
                    mdw.getRequestHeaderOut().getBody().setAny(freezeAccountRQ.getFreezeAccountRQ());
                    return processAdapter.execute(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create(URI_CREATED_FREEZE_ACCOUNT))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }
}
