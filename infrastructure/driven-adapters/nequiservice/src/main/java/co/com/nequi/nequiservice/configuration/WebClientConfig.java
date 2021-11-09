package co.com.nequi.nequiservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.util.MimeTypeUtils.*;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient createWebClient() {
        return WebClient.builder().baseUrl("https://14829b62-d02f-4cf5-b2a8-3c0b3b8f7884.mock.pstmn.io")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .build();
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().encoder(new Jackson2JsonEncoder(new ObjectMapper(), TEXT_HTML, TEXT_PLAIN , TEXT_XML));
        clientCodecConfigurer.customCodecs().decoder(new Jackson2JsonDecoder(new ObjectMapper(), TEXT_HTML, TEXT_PLAIN , TEXT_XML));

    }
}
