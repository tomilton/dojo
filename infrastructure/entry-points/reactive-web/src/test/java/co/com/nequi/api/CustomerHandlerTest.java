package co.com.nequi.api;

import co.com.nequi.api.config.ReflectionUtils;
import co.com.nequi.api.customer.datos.*;
import co.com.nequi.api.models.createcustomer.CustomerJsonMdwRs;
import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.usecase.createcustomer.CreateCustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@ComponentScan({"co.com.nequi.api.requestmdw"})
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class CustomerHandlerTest {

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;
    @InjectMocks
    private CustomerHandler customerHandler;

    @Test
    void createCustomer() throws Exception {

        RequestJsonMdw requestMdw = DatosRequestJsonMdw.buildRequestJsonWithoutDataInTheBody();
        ServerRequest serverRequestMock = mock(ServerRequest.class);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(serverRequestMock.bodyToMono(RequestJsonMdw.class)).thenReturn(Mono.just(requestMdw));
        when(mapper.map(any(), eq(RequestMdw.class))).thenReturn(DatosRequestMdw.buildRequestWithDataInTheBody());
        when(mapper.map(any(), eq(Customer.class))).thenReturn(DatosRequestMdw.buildCustomer());
        when(mapper.map(any(), eq(ResponseJsonMdw.class))).thenReturn(DatosResponseJsonMdw.buildResponseJsonMdw());
        when(mapper.map(any(), eq(CustomerJsonMdwRs.class))).thenReturn(DatosCustomerJsonMdwRs.buildCustomerJsonMdwRs());
        when(createCustomerUseCase.execute(any())).thenReturn(Mono.just(DatosResponseMdw.buildResponseMdw()));

        ReflectionUtils.setFinalStaticField(CustomerHandler.class, "mapper", mapper, customerHandler);
        ReflectionUtils.setFinalStaticField(CustomerHandler.class, "createCustomerUseCase", createCustomerUseCase, customerHandler);
        Mono<ServerResponse> responseHandler = customerHandler.createCustomer(serverRequestMock);
        StepVerifier.create(responseHandler)
                .consumeNextWith(response -> {
                    assertNotNull(response);
                    assertNotNull(response.statusCode());
                    assertEquals(HttpStatus.OK, response.statusCode());
                }).verifyComplete();

    }
}