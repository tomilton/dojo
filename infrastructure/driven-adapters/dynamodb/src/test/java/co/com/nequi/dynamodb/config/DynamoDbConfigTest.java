package co.com.nequi.dynamodb.config;

import co.com.nequi.dynamodb.entity.TemplateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;


class DynamoDbConfigTest {

    String dynamoEndpoint = "http://localhost:4566/";
    DynamoDbConfig dynamoDbConfig;

    @BeforeEach
    void setUp() {
        dynamoDbConfig = new DynamoDbConfig(dynamoEndpoint);
    }

    @Test
    void getDynamoDbAsyncClient() {
        assertNotNull(dynamoDbConfig.getDynamoDbAsyncClient());
    }

    @Test
    void getDynamoDbEnhancedAsyncClient() {
        assertNotNull(dynamoDbConfig.getDynamoDbEnhancedAsyncClient(dynamoDbConfig.getDynamoDbAsyncClient()));
    }

    @Test
    void getDynamoDbAsyncTemplate() {
        assertNotNull(dynamoDbConfig.getDynamoDbAsyncTemplate(dynamoDbConfig.getDynamoDbEnhancedAsyncClient(dynamoDbConfig.getDynamoDbAsyncClient())));
    }
}