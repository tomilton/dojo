package co.com.nequi.dynamodb.service;

import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.datos.DatosTemplateEntity;
import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import co.com.nequi.model.template.Template;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;


import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class TemplateServiceTest {

    @InjectMocks
    TemplateService templateService;

    @InjectMocks
    TemplateDynamoDBRepository templateRepository;

    @Test
    void save() {
        /*DynamoDbAsyncTable<TemplateEntity> templateDynamoDbAsyncTable = null;
        CompletableFuture<TemplateEntity> future = CompletableFuture.supplyAsync(() -> DatosTemplateEntity.buildTemplateEntity());
        when(templateRepository.save(any())).thenReturn(future);
        when(templateService.save(any())).thenReturn(Mono.just(DatosTemplate.buildTemplate()));

        Mockito.doNothing().when(templateDynamoDbAsyncTable.updateItem(DatosTemplateEntity.buildTemplateEntity()));

        Mono<Template> templateMono = templateService.save(TemplateTest.buildTemplate());
        StepVerifier.create(templateMono).expectNext(TemplateTest.buildTemplate()).verifyComplete();*/
    }


}