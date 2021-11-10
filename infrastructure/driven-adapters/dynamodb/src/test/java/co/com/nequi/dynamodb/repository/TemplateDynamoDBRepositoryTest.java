package co.com.nequi.dynamodb.repository;

import co.com.nequi.dynamodb.datos.DatosTemplateEntity;
import co.com.nequi.dynamodb.entity.TemplateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TemplateDynamoDBRepositoryTest {

    private TemplateDynamoDBRepository templateDynamoDBRepository;
    private DynamoDbAsyncTable<TemplateEntity> templateDynamoDbAsyncTable;

    @BeforeEach
    void setUp() {
        this.templateDynamoDbAsyncTable = Mockito.mock(DynamoDbAsyncTable.class);
        this.templateDynamoDBRepository = new TemplateDynamoDBRepository(templateDynamoDbAsyncTable);
    }

    @Test
    void save() throws ExecutionException, InterruptedException {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateDynamoDbAsyncTable.updateItem(templateEntity)).thenReturn(completableFuture);
        CompletableFuture<TemplateEntity> future = templateDynamoDBRepository.save(templateEntity);
        assertTrue(future.isDone());
        TemplateEntity entity = future.get();
        assertEquals("1", entity.getTemplateID());
    }

    @Test
    void update() throws ExecutionException, InterruptedException {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateDynamoDbAsyncTable.updateItem(templateEntity)).thenReturn(completableFuture);
        CompletableFuture<TemplateEntity> future = templateDynamoDBRepository.update(templateEntity);
        assertTrue(future.isDone());
        TemplateEntity entity = future.get();
        assertNotNull(entity);
    }

    @Test
    void delete() throws ExecutionException, InterruptedException {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateDynamoDbAsyncTable.deleteItem(getKeyBuild("1"))).thenReturn(completableFuture);
        CompletableFuture<TemplateEntity> future = templateDynamoDBRepository.delete("1");
        assertTrue(future.isDone());
        TemplateEntity entity = future.get();
        assertNotNull(entity);
    }

    @Test
    void getById() throws ExecutionException, InterruptedException {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        CompletableFuture<TemplateEntity> completableFuture = CompletableFuture.completedFuture(templateEntity);
        when(templateDynamoDbAsyncTable.getItem(getKeyBuild("1"))).thenReturn(completableFuture);
        CompletableFuture<TemplateEntity> future = templateDynamoDBRepository.getByID("1");
        assertTrue(future.isDone());
        TemplateEntity entity = future.get();
        assertNotNull(entity);
    }

    private Key getKeyBuild(String templateId) {
        return Key.builder()
                .partitionValue(templateId)
                .build();
    }


}