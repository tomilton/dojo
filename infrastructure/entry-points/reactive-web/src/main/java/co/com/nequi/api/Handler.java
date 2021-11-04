package co.com.nequi.api;

import co.com.nequi.api.models.CustomerDetailReq;
import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.person.Person;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.template.Template;
import co.com.nequi.usecase.createcustomer.CreateCustomerUseCase;
import co.com.nequi.usecase.getcustomerdetail.GetCustomerDetailUseCase;
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

@Component
@RequiredArgsConstructor
public class Handler {

    private final PersonUseCase useCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerDetailUseCase getCustomerDetailUseCase;
    private final ObjectMapper mapper;

    final static Logger logger = LoggerFactory.getLogger(Handler.class);

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
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(useCase.getAllTemplates(), Template.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createCustomer(ServerRequest request) {
        Mono<RequestJsonMdw> requestMdwMono = request.bodyToMono(RequestJsonMdw.class);

        return requestMdwMono
                .flatMap(requestMdw -> {
                    logger.info(requestMdw.getOmitXMLDeclaration());

                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    Customer customer = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), Customer.class);
                    mdw.getRequestHeaderOut().getBody().setAny(customer);

                    return createCustomerUseCase.createCustomer(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/customer/createCustomer"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(mapper.map(sr, ResponseJsonMdw.class)))
                );
    }

    public Mono<ServerResponse> getCustomerDetails(ServerRequest request) {
        Mono<RequestJsonMdw> requestMdwMono = request.bodyToMono(RequestJsonMdw.class);

        return requestMdwMono
                .flatMap(requestMdw -> {
                    logger.info(requestMdw.getOmitXMLDeclaration());

                    RequestMdw mdw = mapper.map(requestMdw, RequestMdw.class);
                    CustomerDetailReq customerDetailReq = mapper.map(mdw.getRequestHeaderOut().getBody().getAny(), CustomerDetailReq.class);
                    return getCustomerDetailUseCase.getCustomerDetail(mdw);
                })
                .flatMap(sr -> ServerResponse
                        .created(URI.create("/api/customer/getCustomerDetails"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(sr))
                );
    }

}
