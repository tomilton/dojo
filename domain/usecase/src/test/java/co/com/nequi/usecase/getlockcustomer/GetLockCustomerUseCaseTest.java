package co.com.nequi.usecase.getlockcustomer;

import co.com.nequi.model.customer.GetLockRQ;
import co.com.nequi.model.customer.LockRQ;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.model.template.Lock;
import co.com.nequi.model.template.LockRS;
import co.com.nequi.model.template.gateways.LockRepository;
import co.com.nequi.usecase.dataprovider.DataProviderRequest;
import co.com.nequi.usecase.getlockcustomer.datos.DatosResponseFinacle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class GetLockCustomerUseCaseTest {

    @Mock
    private LockRepository lockRepository;
    @Mock
    private CustomerServiceFinacle customerServiceFinacle;

    @InjectMocks
    private GetLockCustomerUseCase getLockCustomerUseCase;

    @Test
    void getLockSuccessServiceCustId() {
        RequestMdw requestMdw = DataProviderRequest.buildRequestLockCustomerTest();
        GetLockRQ getLockRQ = new GetLockRQ();
        LockRQ lockRQ = new LockRQ();
        lockRQ.setIdAccount("1225");
        getLockRQ.setGetLockRQ(lockRQ);
        requestMdw.getRequestHeaderOut().getBody().setAny(getLockRQ);
        when(customerServiceFinacle.getLock(any(), any())).thenReturn(Mono.just(DatosResponseFinacle.responseFinacleSuccess()));
        when(lockRepository.getById(any())).thenReturn(DatosResponseFinacle.responserLockRS());
        Mono<ResponseMdw> responseMdwMono = getLockCustomerUseCase.getLockCustomer(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    LockRS getLockRepository = lockRepository.getById(any());
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof Lock);
                    Lock lockResponse = (Lock) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(lockResponse.getGetLockRS().getCustId(), "1225");
                }).verifyComplete();
    }


}