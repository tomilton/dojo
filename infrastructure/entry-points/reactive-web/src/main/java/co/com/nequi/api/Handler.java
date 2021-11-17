package co.com.nequi.api;

import co.com.nequi.api.models.createcustomer.CustomerJsonMdwRs;
import co.com.nequi.api.models.createcustomer.LiteRegistryBrokerRS;
import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.person.Person;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.template.Template;
import co.com.nequi.usecase.createcustomer.CreateCustomerUseCase;
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

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class Handler {

    private final PersonUseCase useCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final ObjectMapper mapper;

    static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public Mono<ServerResponse> getPerson(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getPerson(id), Person.class);
    }

    public Mono<ServerResponse> getTemplate(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getTemplateById(id), Template.class);
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

    public Mono<ServerResponse> getCustomerDetails(ServerRequest request) {
        Mono<RequestJsonMdw> requestMdwMono = request.bodyToMono(RequestJsonMdw.class);

        return requestMdwMono
                .flatMap(requestMdw -> {
                    logger.info(requestMdw.getOmitXMLDeclaration());

                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);

                    return createCustomerUseCase.execute(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/customer/createCustomer"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(sr))
                );
    }

}
