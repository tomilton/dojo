package co.com.nequi.dynamodb.repository;

import co.com.nequi.dynamodb.entity.TemplateEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Repository
public class TemplateDynamoDBRepository {

    private final DynamoDbAsyncTable<TemplateEntity> templateDynamoDbAsyncTable;

    public TemplateDynamoDBRepository(DynamoDbAsyncTable<TemplateEntity> templateDynamoDbAsyncTable) {
        this.templateDynamoDbAsyncTable = templateDynamoDbAsyncTable;
    }

    /**
     * Se puede usar putItem
     *
     * @param template
     * @return
     */
    public CompletableFuture<TemplateEntity> save(TemplateEntity template) {
        return templateDynamoDbAsyncTable.updateItem(template);
    }

    public CompletableFuture<TemplateEntity> getByID(String templateId) {
        return templateDynamoDbAsyncTable.getItem(getKeyBuild(templateId));
    }

    public CompletableFuture<TemplateEntity> update(TemplateEntity template) {
        return templateDynamoDbAsyncTable.updateItem(template);
    }

    public CompletableFuture<TemplateEntity> delete(String id) {
        return templateDynamoDbAsyncTable.deleteItem(getKeyBuild(id));
    }

    public PagePublisher<TemplateEntity> getAll() {return templateDynamoDbAsyncTable.scan();}

    private Key getKeyBuild(String templateId) {
        return Key.builder()
                .partitionValue(templateId)
                .build();
    }

}
