package co.com.nequi.usecase.unfreezeaccount;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.AccountOperationException;
import co.com.nequi.model.requestmdw.*;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.usecase.dataprovider.DataProviderRequest;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
public class UnFreezeAccountUseCaseTest {
    @Mock
    private AccountService accountService;

    @Mock
    private CustomerDefaultDataRepository customerDefaultDataRepository;

    @InjectMocks
    private UnFreezeAccountUseCase unFreezeAccountUseCase;

    @Test
    public void unFreezeAccountSuccessService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestUnFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        UnFreezeAccountRsService unFreezeAccountRsService = new UnFreezeAccountRsService("true");
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        when(accountService.unFreezeAccount(any())).thenReturn(Mono.just(unFreezeAccountRsService));
        Mono<ResponseMdw> responseMdwMono = unFreezeAccountUseCase.unFreezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof UnFreezeAccountBrokerRS);
                    UnFreezeAccountBrokerRS unFreezeAccountBrokerRS = (UnFreezeAccountBrokerRS) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(unFreezeAccountBrokerRS.getSelfClosing(),"true");
                }).verifyComplete();
    }

    @Test
    public void unFreezeAccountFailedService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestUnFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        UnFreezeAccountRsService unFreezeAccountRsService = new UnFreezeAccountRsService("false");
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        when(accountService.unFreezeAccount(any())).thenReturn(Mono.just(unFreezeAccountRsService));
        Mono<ResponseMdw> responseMdwMono = unFreezeAccountUseCase.unFreezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof UnFreezeAccountBrokerRS);
                    UnFreezeAccountBrokerRS unFreezeAccountBrokerRS = (UnFreezeAccountBrokerRS) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(unFreezeAccountBrokerRS.getSelfClosing(),"false");
                }).verifyComplete();
    }

    @Test
    public void unFreezeAccountErrorService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestUnFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        when(accountService.unFreezeAccount(any())).thenReturn(Mono.error(new AccountOperationException("ERROR INTERNO SERVIDOR")));
        Mono<ResponseMdw> responseMdwMono = unFreezeAccountUseCase.unFreezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof UnFreezeAccountBrokerRS);
                    UnFreezeAccountBrokerRS unFreezeAccountBrokerRS = (UnFreezeAccountBrokerRS) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(unFreezeAccountBrokerRS.getSelfClosing(),"ERROR INTERNO SERVIDOR");
                }).verifyComplete();
    }

}
