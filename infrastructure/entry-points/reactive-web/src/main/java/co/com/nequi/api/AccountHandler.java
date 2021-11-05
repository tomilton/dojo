package co.com.nequi.api;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@RequiredArgsConstructor
public class AccountHandler {
    private  FreezeAccountUseCase freezeUserCase;
    private final ObjectMapper mapper;

    public Mono<ServerResponse> freezeAccount(ServerRequest serverRequest){
        //TODO se debe cambiar el objeto a el request completo
        Mono<RequestJsonMdw> freezeAccountRqDtoMono = serverRequest.bodyToMono(RequestJsonMdw.class);

        return freezeAccountRqDtoMono
                .flatMap(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    Customer customer = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), Customer.class);
                    mdw.getRequestHeaderOut().getBody().setAny(customer);

                    return freezeUserCase.freezeAccount(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/account/freezeAccount"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }
}
