package co.com.nequi.dynamodb.service;

import co.com.nequi.dynamodb.mapper.Mapper;
import co.com.nequi.model.template.Template;
import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import co.com.nequi.model.template.gateways.TemplateRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;


@Service
public class TemplateService implements TemplateRepository {

    public static final Logger log = LoggerFactory.getLogger(TemplateService.class);

    private final TemplateDynamoDBRepository templateRepository;

    private final ObjectMapper mapper;

    public TemplateService(TemplateDynamoDBRepository templateRepository, ObjectMapper mapper) {
        this.templateRepository = templateRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Template> save(Template template) {
        return Mono.fromFuture(templateRepository.save(Mapper.toEntity(template)))
                .map(Mapper::toData)
                .onErrorReturn(new Template());
    }

    @Override
    public Mono<Template> update(Template template) {
        return Mono.fromFuture(templateRepository.update(Mapper.toEntity(template)))
                .map(Mapper::toData)
                .onErrorReturn(new Template());
    }

    @Override
    public Mono<Template> delete(String id) {
        return Mono.fromFuture(templateRepository.delete(id))
                .map(Mapper::toData)
                .onErrorReturn(new Template());
    }

    @Override
    public Flux<Template> getAll() {
        return Flux.from(templateRepository.getAll().items()).log().repeat(2).delayElements(Duration.ofSeconds(1)).map(Mapper::toData).limitRate(2).onErrorReturn(new Template());
    }

    @Override
    public Mono<Template> getById(final String id) {
        return Mono.fromFuture(templateRepository.getByID(id))
                .log()
                .doOnSuccess(Objects::requireNonNull)
                .map(Mapper::toData)
                .onErrorReturn(new Template());
    }
}
