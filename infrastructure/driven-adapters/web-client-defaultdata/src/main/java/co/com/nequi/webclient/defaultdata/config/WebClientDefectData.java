package co.com.nequi.webclient.defaultdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientDefectData {

    @Value("${config.base.endpoint}")
    private String url;

    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder().baseUrl(url);
    }
}
