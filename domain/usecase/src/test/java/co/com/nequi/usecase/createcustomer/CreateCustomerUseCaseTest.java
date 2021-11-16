package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.CustomerRS;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.exceptions.DefaultDataException;
import co.com.nequi.model.requestmdw.*;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.model.responsemdw.ResponseStatus;
import co.com.nequi.usecase.createcustomer.datos.DatosRequestMdw;
import co.com.nequi.usecase.createcustomer.datos.DatosResponseFinacle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerDefaultDataRepository defaultDataRepository;
    @Mock
    private CustomerServiceFinacle customerServiceFinacle;
    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;


    @Test
    void createCustomerWithoutDataInTheBody() {
        RequestMdw requestMdw = DatosRequestMdw.buildRequestWithoutDataInTheBody();
        CustomerResponseFinacle responseFinacle = DatosResponseFinacle.buildCustomerResponseSuccess();
        List<CustomerDefaultData> defaultDataList = new ArrayList<>();
        when(defaultDataRepository.getDefaultData(any())).thenReturn(Flux.fromIterable(defaultDataList));
        when(customerServiceFinacle.save(any())).thenReturn(Mono.just(responseFinacle));

        Mono<ResponseMdw> responseMdwMono = createCustomerUseCase.createCustomer(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {

                    ResponseStatus responseStatus = responseParam.getResponseHeaderOut().getHeader().getResponseStatus();
                    assertEquals("500", responseStatus.getStatusCode());
                    assertNotNull(responseStatus.getErrorMessage());

                }).verifyComplete();
    }


    @Test
    void createCustomerSuccess() {
        RequestMdw requestMdw = DatosRequestMdw.buildRequestWithDataInTheBody();
        CustomerResponseFinacle responseFinacle = DatosResponseFinacle.buildCustomerResponseSuccess();
        List<CustomerDefaultData> defaultDataList = new ArrayList<>();
        when(defaultDataRepository.getDefaultData(any())).thenReturn(Flux.fromIterable(defaultDataList));
        when(customerServiceFinacle.save(any())).thenReturn(Mono.just(responseFinacle));
        Mono<ResponseMdw> responseMdwMono = createCustomerUseCase.createCustomer(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {

                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof CustomerRS);
                    CustomerRS customerRS = (CustomerRS) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals("NP16000000239824", customerRS.getLiteRegistryBrokerRS().getCifId());

                }).verifyComplete();
    }

    @Test
    void createCustomerErrorServiceFinacle() {
        RequestMdw requestMdw = DatosRequestMdw.buildRequestWithDataInTheBody();
        List<CustomerDefaultData> defaultDataList = new ArrayList<>();
        when(defaultDataRepository.getDefaultData(any())).thenReturn(Flux.fromIterable(defaultDataList));
        when(customerServiceFinacle.save(any())).thenReturn(Mono.error(new CreateCustomerFinacleException("Error Finacle")));
        Mono<ResponseMdw> responseMdwMono = createCustomerUseCase.createCustomer(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(response -> {

                    ResponseStatus responseStatus = response.getResponseHeaderOut().getHeader().getResponseStatus();
                    assertTrue(response instanceof ResponseMdw);
                    assertEquals("500", responseStatus.getStatusCode());
                    assertNotNull(responseStatus.getErrorMessage());

                }).verifyComplete();
    }

    @Test
    void createCustomerErrorServiceDefaultData() {
        RequestMdw requestMdw = DatosRequestMdw.buildRequestWithDataInTheBody();
        CustomerResponseFinacle responseFinacle = DatosResponseFinacle.buildCustomerResponseSuccess();
        when(defaultDataRepository.getDefaultData(any())).thenReturn(Flux.error(new DefaultDataException("Error DefaultData")));
        when(customerServiceFinacle.save(any())).thenReturn(Mono.just(responseFinacle));
        Mono<ResponseMdw> responseMdwMono = createCustomerUseCase.createCustomer(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(response -> {

                    ResponseStatus responseStatus = response.getResponseHeaderOut().getHeader().getResponseStatus();
                    assertTrue(response instanceof ResponseMdw);
                    assertEquals("500", responseStatus.getStatusCode());
                    assertNotNull(responseStatus.getErrorMessage());

                }).verifyComplete();
    }


}