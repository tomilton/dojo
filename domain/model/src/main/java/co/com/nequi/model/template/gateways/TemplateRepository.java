package co.com.nequi.model.template.gateways;

import co.com.nequi.model.template.Template;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TemplateRepository {

    Mono<Template> save(Template template);

    Mono<Template> update(Template template);

    Mono<Template> delete(String id);

    Flux<Template> getAll();

    Mono<Template> getById(String id);

}
