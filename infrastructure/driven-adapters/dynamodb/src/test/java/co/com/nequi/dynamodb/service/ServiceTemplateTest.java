package co.com.nequi.dynamodb.service;

/*import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.datos.DatosTemplateEntity;
import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import co.com.nequi.model.template.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ServiceTemplateTest {

    private TemplateService templateService;
    private TemplateDynamoDBRepository templateRepository;


    @BeforeEach
    void setUp() {
        this.templateRepository = Mockito.mock(TemplateDynamoDBRepository.class);
        this.templateService = new TemplateService(templateRepository);
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
}*/