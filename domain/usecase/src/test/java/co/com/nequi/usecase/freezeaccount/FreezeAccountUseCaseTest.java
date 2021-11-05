package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.exceptions.AccountOperationException;
import co.com.nequi.model.requestmdw.*;
import co.com.nequi.model.responsemdw.ResponseMdw;
import org.junit.jupiter.api.Assertions;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
public class FreezeAccountUseCaseTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private FreezeAccountUseCase freezeAccountUseCase;

    @Test
    public void freezeAccountSuccessService(){
        RequestMdw requestMdw = this.buildRequestDefaultTest();
        FreezeAccountRsService freezeAccountRsService = new FreezeAccountRsService(Boolean.TRUE,"");
        when(accountService.freezeAccount(any())).thenReturn(Mono.just(freezeAccountRsService));
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
        RequestMdw requestMdw = this.buildRequestDefaultTest();
        FreezeAccountRsService freezeAccountRsService = new FreezeAccountRsService(Boolean.FALSE,"CUENTA NO EXISTE");
        when(accountService.freezeAccount(any())).thenReturn(Mono.just(freezeAccountRsService));
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
        RequestMdw requestMdw = this.buildRequestDefaultTest();
        when(accountService.freezeAccount(any())).thenReturn(Mono.error(new AccountOperationException("ERROR INTERNO SERVIDOR")));
        Mono<ResponseMdw> responseMdwMono = freezeAccountUseCase.freezeAccount(requestMdw);
        StepVerifier.create(responseMdwMono)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam.getResponseHeaderOut().getBody().getAny());
                    assertTrue(responseParam.getResponseHeaderOut().getBody().getAny() instanceof FreezeAccountRs);
                    FreezeAccountRs freezeAccountRs = (FreezeAccountRs) responseParam.getResponseHeaderOut().getBody().getAny();
                    assertEquals(freezeAccountRs.getFreezeAccountRs(),"ERROR CUENTA CONGELADA");
                });
    }

    private RequestMdw buildRequestDefaultTest(){
        RequestMdw requestMdw = new RequestMdw();
        RequestHeaderOut requestHeaderOut = new RequestHeaderOut();
        SecurityCredential securityCredential = new SecurityCredential();
        securityCredential.setUserName("BROKER");
        securityCredential.setUserToken("TOKEN");
        Header header = new Header();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setSecurityCredential(securityCredential);
        Destination destination = new Destination();
        destination.setName("Account");
        destination.setNamespace("http://co.bancaDigital/nequi/services/AccountServices/Account/v1.0");
        destination.setOperation("freezeAccount");
        header.setDestination(destination);
        MessageContext messageContext = new MessageContext();
        Property property = new Property();
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setKey("RQ");
        item1.setValue("freezeAccountRQ");
        items.add(item1);
        Item item2 = new Item();
        item2.setKey("RS");
        item2.setValue("freezeAccountRS");
        items.add(item2);
        Item item3 = new Item();
        item3.setKey("Region");
        item3.setValue("C001");
        items.add(item3);
        Item item4 = new Item();
        item4.setKey("appType");
        item4.setValue("Nequi");
        items.add(item4);
        property.setItem(items);
        messageContext.setProperty(property);
        header.setMessageContext(messageContext);
        requestHeaderOut.setHeader(header);
        Body body = new Body();
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode("10");
        freezeAccountRqDto.setFreezeCode("D");
        body.setAny(freezeAccountRqDto);
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }
}
