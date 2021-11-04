package co.com.nequi.nequiservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
    @Bean
    public WebClient createWebClient() {
        return WebClient.builder().baseUrl("https://14829b62-d02f-4cf5-b2a8-3c0b3b8f7884.mock.pstmn.io")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
