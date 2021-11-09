package co.com.nequi.dynamodb.controller;

import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.service.TemplateService;
import co.com.nequi.model.template.Template;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TemplateController.class)
@EnableAutoConfiguration
class TemplateControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private TemplateService templateService;

    @Test
    void save() {
        when(templateService.save(any())).thenReturn(Mono.just(DatosTemplate.buildTemplate()));
        client.post()
                .uri("/api/template/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(DatosTemplate.buildTemplate())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Template.class)
                .consumeWith(response -> {
                    Template template = response.getResponseBody();
                    Assertions.assertThat(template.getTemplateID()).isNotNull();
                });
    }


}