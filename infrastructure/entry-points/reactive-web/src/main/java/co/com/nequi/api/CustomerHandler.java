package co.com.nequi.api;

import co.com.nequi.api.models.createcustomer.CustomerJsonMdwRs;
import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.usecase.createcustomer.CreateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ObjectMapper mapper;

    public Mono<ServerResponse> createCustomer(ServerRequest request) {
        Mono<RequestJsonMdw> requestMdwMono = request.bodyToMono(RequestJsonMdw.class);
        return requestMdwMono
                .map(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    Customer customer = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), Customer.class);
                    mdw.getRequestHeaderOut().getBody().setAny(customer);
                    return mdw;
                })
                .flatMap(createCustomerUseCase::execute)
                .map(responseMdw -> {
                    ResponseJsonMdw mdw = mapper.map(responseMdw, ResponseJsonMdw.class);
                    CustomerJsonMdwRs brokerRS = mapper.map(mdw.getResponseHeaderOut().getBody().getAny(), CustomerJsonMdwRs.class);
                    mdw.getResponseHeaderOut().getBody().setAny(brokerRS);
                    return mdw;
                })
                .flatMap(sr -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(sr))
                );
    }


}
