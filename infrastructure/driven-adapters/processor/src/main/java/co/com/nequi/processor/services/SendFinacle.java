package co.com.nequi.processor.services;

import co.com.nequi.model.template.Template;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SendFinacle {
    @Autowired
    public WebClient webClient;

    public Mono<String> execute(Template template, JSONObject body){
        System.out.println("body send to finacle "+body);
        System.out.println("path to call  "+template.getPath());
        return webClient.post()
                .uri(template.getPath(),"1600")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body.toString())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.just(new Exception("error 500")))
                .bodyToMono(String.class);

    }
}
