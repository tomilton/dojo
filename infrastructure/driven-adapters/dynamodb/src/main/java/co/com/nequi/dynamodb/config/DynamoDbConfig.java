package co.com.nequi.dynamodb.config;

import co.com.nequi.dynamodb.entity.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;


import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class DynamoDbConfig implements CommandLineRunner {

    @Value("${aws.accessKey}")
    String accessKey;

    @Value("${aws.secretKey}")
    String secretKey;


    private final String dynamoEndpoint;


    public DynamoDbConfig(@Value("${dynamodb.endpoint:}") String dynamoEndpoint) {
        this.dynamoEndpoint = dynamoEndpoint;
    }

    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .endpointOverride(URI.create(dynamoEndpoint))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient(DynamoDbAsyncClient dynamoDbAsyncClient) {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(dynamoDbAsyncClient)
                .build();
    }

    @Bean
    public DynamoDbAsyncTable<Template> getDynamoDbAsyncTemplate(DynamoDbEnhancedAsyncClient asyncClient) {
        return asyncClient.table(Template.class.getSimpleName(), TableSchema.fromBean(Template.class));
    }

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = getDynamoDbAsyncClient().listTables();
        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
        listCompletableFuture.thenAccept(tables -> {
            if (null != tables && !tables.contains(Template.class.getSimpleName())) {
                DynamoDbAsyncTable<Template> template = getDynamoDbEnhancedAsyncClient(getDynamoDbAsyncClient()).table(Template.class.getSimpleName(), TableSchema.fromBean(Template.class));
                template.createTable();
            }
        });
    }
}
