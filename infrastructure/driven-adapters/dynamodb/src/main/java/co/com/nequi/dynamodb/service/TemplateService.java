package co.com.nequi.dynamodb.service;

import co.com.nequi.dynamodb.mapper.Mapper;
import co.com.nequi.model.template.Template;
import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import co.com.nequi.model.template.gateways.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class TemplateService implements TemplateRepository {

    public static final Logger log = LoggerFactory.getLogger(TemplateService.class);

    private final TemplateDynamoDBRepository templateRepository;

    public TemplateService(TemplateDynamoDBRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Mono<Template> save(Template template) {
        return Mono.fromFuture(templateRepository.save(Mapper.toEntity(template)))
                .map(Mapper::toData);
    }

    @Override
    public Mono<Template> update(Template template) {
        return null;
    }

    @Override
    public Mono<Template> delete(String id) {
        return null;
    }

    @Override
    public Flux<Template> getAll() {
        return null;
    }

    @Override
    public Mono<Template> getById(final String id) {
        /*return Mono.fromFuture(templateRepository.getByID(id))
                .log()
                // .doOnSuccess(Objects::requireNonNull)
                .map(Mapper::toData);*/


            return Mono.fromFuture(templateRepository.getByID(id))
                    .flatMap(data ->{
                        return Mono.just(data);
                    })
                    .doOnNext(i -> log.info("Hola: " + i.toString()))
                    .map(Mapper::toData)
                    .doOnSuccess(Objects::requireNonNull)
                    .onErrorReturn(new Template());


    }
}
