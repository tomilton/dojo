package co.com.nequi.api;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.customer.GetLockRQ;
import co.com.nequi.model.person.Person;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.template.Template;
import co.com.nequi.usecase.createcustomer.CreateCustomerUseCase;
import co.com.nequi.usecase.getcustomerdetail.GetCustomerDetailUseCase;
import co.com.nequi.usecase.getlockcustomer.GetLockCustomerUseCase;
import co.com.nequi.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class Handler {

    private final PersonUseCase useCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerDetailUseCase getCustomerDetailUseCase;
    private final ObjectMapper mapper;
    private final GetLockCustomerUseCase getLockCustomerUseCase;

    static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public Mono<ServerResponse> getPerson(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getPerson(id), Person.class);
    }

    public Mono<ServerResponse> getTemplate(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getTemplateById(id), Template.class);
    }
    public Mono<ServerResponse> getLockCustomer(ServerRequest serverRequest) {
        Mono<RequestJsonMdw> requestMdwMono = serverRequest.bodyToMono(RequestJsonMdw.class);
        return requestMdwMono
                .map(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    GetLockRQ lockRQ = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), GetLockRQ.class);
                    mdw.getRequestHeaderOut().getBody().setAny(lockRQ);
                    return mdw;
                })
                .flatMap(getLockCustomerUseCase::getLockCustomer)
                .flatMap(sr -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(sr))
                );
    }
    public Mono<ServerResponse> getAllTemplates(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(useCase.getAllTemplates(), Template.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createTemplate(ServerRequest request) {
        Mono<Template> requestMdwMono = request.bodyToMono(Template.class);
        return requestMdwMono
                .flatMap(useCase::saveTemplate)
                .flatMap(sr -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(sr))
                );
    }

    public Mono<ServerResponse> getCustomerDetails(ServerRequest request) {
        Mono<RequestJsonMdw> requestMdwMono = request.bodyToMono(RequestJsonMdw.class);

        return requestMdwMono
                .map(requestMdw -> {
                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    CustomerDetailReq customerDetailReq = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), CustomerDetailReq.class);
                    mdw.getRequestHeaderOut().getBody().setAny(customerDetailReq);
                    return mdw;
                })
                .flatMap(getCustomerDetailUseCase::getCustomerDetail)
                .map(responseMdw -> {
                    ResponseJsonMdw mdw = mapper.map(responseMdw, ResponseJsonMdw.class);
                    return mdw;
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/customer/getCustomerDetails"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(sr))
                );
    }

}
