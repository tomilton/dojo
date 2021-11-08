package co.com.nequi.webclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${web.client}")
    private String url;

    @Bean
    public WebClient registrarWebClient() {
        return WebClient.builder().baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Endpoint {} ", url);
    }
}
