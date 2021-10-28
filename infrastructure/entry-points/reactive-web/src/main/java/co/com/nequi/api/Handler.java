package co.com.nequi.api;

import co.com.nequi.model.person.Person;
import co.com.nequi.model.template.Template;
import co.com.nequi.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final PersonUseCase useCase;

    public Mono<ServerResponse> getPerson(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getPerson(id), Person.class);
    }

    public Mono<ServerResponse> getTemplate(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(useCase.getTemplateById(id), Template.class);
    }

    public Mono<ServerResponse> getAllTemplates(ServerRequest serverRequest) {
        return ServerResponse.ok().body(useCase.getAllTemplates(), Template.class);
    }

}
