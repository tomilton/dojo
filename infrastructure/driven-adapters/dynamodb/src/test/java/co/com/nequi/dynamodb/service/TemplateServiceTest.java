package co.com.nequi.dynamodb.service;

import co.com.nequi.dynamodb.repository.TemplateDynamoDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;


@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class TemplateServiceTest {


    @InjectMocks
    TemplateService templateService;

    @InjectMocks
    TemplateDynamoDBRepository templateRepository;

    // @InjectMocks
   

    @Test
    void save() {
        /*DynamoDbAsyncTable<TemplateEntity> templateDynamoDbAsyncTable = null;
        CompletableFuture<TemplateEntity> future = CompletableFuture.supplyAsync(() -> TemplateEntityTest.buildTemplateEntity());
        when(templateRepository.save(any())).thenReturn(future);
        when(templateService.save(any())).thenReturn(Mono.just(TemplateTest.buildTemplate()));

        Mockito.doNothing().when(templateDynamoDbAsyncTable.updateItem(TemplateEntityTest.buildTemplateEntity()));
        // Mockito.doNothing().when(templateService.save(any()))

        Mono<Template> templateMono = templateService.save(TemplateTest.buildTemplate());
        StepVerifier.create(templateMono).expectNext(TemplateTest.buildTemplate()).verifyComplete();*/
    }


}