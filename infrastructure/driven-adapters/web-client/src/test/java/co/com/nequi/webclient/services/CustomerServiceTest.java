package co.com.nequi.webclient.services;

import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.webclient.datos.DatosRequestFinacle;
import co.com.nequi.webclient.datos.DatosRequestMiddleware;
import co.com.nequi.webclient.datos.DatosResponseFinacle;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;
    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;

    @InjectMocks
    private CustomerServiceFinacleImpl customerServiceFinacle;

    @Test
    @DisplayName("Respuesta sin errores de finacle")
    void callFinacleCreateCustomerSuccesMockito() {
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1.0/banks/1500/cif/api/Retail/create")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(ArgumentMatchers.any())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CustomerResponseJSON>>notNull())).thenReturn(Mono.just(DatosResponseFinacle.buildCustomerResponseSuccesJSON()));
        when(requestBodySpecMock.header(any(), any())).thenReturn(requestBodySpecMock);
        when(requestHeadersMock.header(any(), any())).thenReturn(requestHeadersMock);
        Mono<CustomerResponseFinacle> response = customerServiceFinacle.save(DatosRequestFinacle.buildRequestFinacle(DatosRequestMiddleware.buildCustomer()));
        StepVerifier.create(response).expectNextMatches(responseService -> responseService.getMeta().getErrorDetails().isEmpty()).verifyComplete();
    }


    @Test
    @DisplayName("Respuesta con errores de finacle")
    void callFinacleCreateCustomerReturnFailedMockito() {
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1.0/banks/1500/cif/api/Retail/create")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(ArgumentMatchers.any())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CustomerResponseJSON>>notNull())).thenReturn(Mono.just(DatosResponseFinacle.buildCustomerResponseErrorJSON()));
        when(requestBodySpecMock.header(any(), any())).thenReturn(requestBodySpecMock);
        when(requestHeadersMock.header(any(), any())).thenReturn(requestHeadersMock);
        Mono<CustomerResponseFinacle> response = customerServiceFinacle.save(DatosRequestFinacle.buildRequestFinacle(DatosRequestMiddleware.buildCustomer()));
        StepVerifier.create(response).expectNextMatches(responseService -> !responseService.getMeta().getErrorDetails().isEmpty()).verifyComplete();
    }

    @Test
    @DisplayName("Respuesta con errores en el servidor de finacle")
    void callCreateCustomerReturn5xxErrorMockito() {
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1.0/banks/1500/cif/api/Retail/create")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(ArgumentMatchers.any())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CustomerResponseJSON>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        when(requestBodySpecMock.header(any(), any())).thenReturn(requestBodySpecMock);
        when(requestHeadersMock.header(any(), any())).thenReturn(requestHeadersMock);
        Mono<CustomerResponseFinacle> response = customerServiceFinacle.save(DatosRequestFinacle.buildRequestFinacle(DatosRequestMiddleware.buildCustomer()));
        StepVerifier.create(response).expectError(CreateCustomerFinacleException.class).verify();
    }

    @Test
    @DisplayName("Error al consultar el api de finacle")
    void callCreateCustomerReturnExceptionMockito() {
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1.0/banks/1500/cif/api/Retail/create")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(ArgumentMatchers.any())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CustomerResponseJSON>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        when(requestBodySpecMock.header(any(), any())).thenReturn(requestBodySpecMock);
        when(requestHeadersMock.header(any(), any())).thenReturn(requestHeadersMock);
        Mono<CustomerResponseFinacle> response = customerServiceFinacle.save(DatosRequestFinacle.buildRequestFinacle(DatosRequestMiddleware.buildCustomer()));
        StepVerifier.create(response).expectError(CreateCustomerFinacleException.class).verify();
    }


}