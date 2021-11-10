package co.com.nequi.dynamodb.controller;

import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.service.TemplateService;
import co.com.nequi.model.template.Template;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TemplateController.class)
@EnableAutoConfiguration
class TemplateControllerTest {

    @Autowired
    private WebTestClient client;

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;
    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @MockBean
    private TemplateService templateService;

    @Test
    void testSave() {
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/api/template/save")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(ArgumentMatchers.any())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Template>>notNull())).thenReturn(Mono.just(DatosTemplate.buildTemplate()));
        when(templateService.save(any())).thenReturn(Mono.just(DatosTemplate.buildTemplate()));
        Mono<Template> response = templateService.save(DatosTemplate.buildTemplate());
        StepVerifier.create(response).expectNextMatches(responseService -> !responseService.getTemplateID().isEmpty()).verifyComplete();
    }

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


    @Test
    void getTemplate() {
        when(templateService.getById(any())).thenReturn(Mono.just(DatosTemplate.buildTemplate()));
        client.get()
                .uri("/api/template/getTemplate/1")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Template.class)
                .consumeWith(response -> {
                    Template template = response.getResponseBody();
                    Assertions.assertThat(template.getTemplateID()).isNotEmpty();
                    Assertions.assertThat(template.getTemplateID().length() > 0).isTrue();
                    Assertions.assertThat(template.getHttpVerb()).isEqualTo("GET");
                });
    }

    @Test
    void getAll() {
        when(templateService.getAll()).thenReturn(Flux.just(new Template()));
        client.get()
                .uri("/api/template/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Template.class)
                .consumeWith(response -> {
                    List<Template> templates = response.getResponseBody();
                    Assertions.assertThat(templates.size() > 0).isTrue();
                });
    }

}