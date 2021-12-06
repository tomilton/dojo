package co.com.nequi.dynamodb.service;

import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.datos.DatosTemplateEntity;
import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import co.com.nequi.model.template.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TemplateServiceTest {

    private TemplateService templateService;
    private TemplateDynamoDBRepository templateRepository;
    @Mock
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.templateRepository = Mockito.mock(TemplateDynamoDBRepository.class);
        this.templateService = new TemplateService(templateRepository, mapper);
    }

    @Test
    void save() {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        Template template = DatosTemplate.buildTemplate();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateRepository.save(any())).thenReturn(completableFuture);
        Mono<Template> templateMono = templateService.save(template);
        StepVerifier.create(templateMono).expectNextMatches(responseService -> !responseService.getTemplateID().isEmpty()).verifyComplete();
    }

    @Test
    void update() {
        String host = "192.168.18.35";
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        templateEntity.setHost(host);
        Template template = DatosTemplate.buildTemplate();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateRepository.update(any())).thenReturn(completableFuture);
        Mono<Template> templateMono = templateService.update(template);
        StepVerifier.create(templateMono).expectNextMatches(responseService -> responseService.getHost().equals(host)).verifyComplete();
    }

    @Test
    void delete() {
        String id = "1";
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateRepository.delete(id)).thenReturn(completableFuture);
        Mono<Template> templateMono = templateService.delete(id);
        StepVerifier.create(templateMono).expectNextCount(1).verifyComplete();
    }

    @Test
    void getById() {
        String id = "1";
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateRepository.getByID(id)).thenReturn(completableFuture);
        Mono<Template> templateMono = templateService.getById(id);
        StepVerifier.create(templateMono).expectNextMatches(responseService -> !responseService.getTemplateID().isEmpty()).verifyComplete();
    }


}