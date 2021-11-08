package co.com.nequi.dynamodb.repository;

import co.com.nequi.dynamodb.entity.Template;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Service
public class TemplateDynamoDBRepository {

    private final DynamoDbAsyncTable<Template> templateDynamoDbAsyncTable;

    public TemplateDynamoDBRepository(DynamoDbAsyncTable<Template> templateDynamoDbAsyncTable) {
        this.templateDynamoDbAsyncTable = templateDynamoDbAsyncTable;
    }

    /**
     * Se puede usar putItem
     *
     * @param template
     * @return
     */
    public CompletableFuture<Template> save(Template template) {
        return templateDynamoDbAsyncTable.updateItem(template);
    }

    public CompletableFuture<Template> getByID(String templateId) {
        return templateDynamoDbAsyncTable.getItem(getKeyBuild(templateId));
    }

    public CompletableFuture<Template> update(Template template) {
        return templateDynamoDbAsyncTable.updateItem(template);
    }

    public CompletableFuture<Template> delete(String id) {
        return templateDynamoDbAsyncTable.deleteItem(getKeyBuild(id));
    }

    public PagePublisher<Template> getAll() {
        return templateDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String templateId) {
        return Key.builder()
                .partitionValue(templateId)
                .build();
    }

}
