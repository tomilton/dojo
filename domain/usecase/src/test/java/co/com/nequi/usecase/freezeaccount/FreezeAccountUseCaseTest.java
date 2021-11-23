package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
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
import reactor.core.publisher.Flux;
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
public class FreezeAccountUseCaseTest {

    @Mock
    private AccountService accountService;

    @Mock
    private CustomerDefaultDataRepository customerDefaultDataRepository;

    @InjectMocks
    private FreezeAccountUseCase freezeAccountUseCase;

    @Test
    public void freezeAccountSuccessService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        FreezeAccountRsService freezeAccountRsService = new FreezeAccountRsService(Boolean.TRUE,"");
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        when(accountService.freezeAccount(any(),any())).thenReturn(Mono.just(freezeAccountRsService));
        Mono<ResponseMdw> responseMdwMono = freezeAccountUseCase.freezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof FreezeAccountRs);
                    FreezeAccountRs freezeAccountRs = (FreezeAccountRs) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(freezeAccountRs.getFreezeAccountRs(),"");
                }).verifyComplete();
    }

    @Test
    public void freezeAccountFailedService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        FreezeAccountRsService freezeAccountRsService = new FreezeAccountRsService(Boolean.FALSE,"CUENTA NO EXISTE");
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        when(accountService.freezeAccount(any(),any())).thenReturn(Mono.just(freezeAccountRsService));
        Mono<ResponseMdw> responseMdwMono = freezeAccountUseCase.freezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof FreezeAccountRs);
                    FreezeAccountRs freezeAccountRs = (FreezeAccountRs) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(freezeAccountRs.getFreezeAccountRs(),"CUENTA NO EXISTE");
                }).verifyComplete();
    }

    @Test
    public void freezeAccountErrorService(){
        RequestMdw requestMdw = DataProviderRequest.buildRequestFreezeAccountDefaultTest();
        CustomerDefaultData defaultData = DataProviderRequest.buildCustomerDefaultData();
        when(accountService.freezeAccount(any(),any())).thenReturn(Mono.error(new AccountOperationException("ERROR INTERNO SERVIDOR")));
        when(customerDefaultDataRepository.getDefaultDataId(eq(157),eq("3"))).thenReturn(Mono.just(defaultData));
        Mono<ResponseMdw> responseMdwMono = freezeAccountUseCase.freezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof FreezeAccountRs);
                    FreezeAccountRs freezeAccountRs = (FreezeAccountRs) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(freezeAccountRs.getFreezeAccountRs(),"ERROR CUENTA CONGELADA");
                });
    }
}
