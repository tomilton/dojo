package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomData;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomDataDetail;
import co.com.nequi.model.exceptions.AccountOperationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void callFinacleFreezeAccountReturnSuccessMockito(){
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        FinacleResponse serviceResponse = new FinacleResponse();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs();
        FreezeAccountRsCustomData freezeAccountRsCustomData = new FreezeAccountRsCustomData();
        FreezeAccountRsCustomDataDetail freezeAccountRsCustomDataDetail = new FreezeAccountRsCustomDataDetail();
        freezeAccountRsCustomDataDetail.setMessage("SUCCESS");
        freezeAccountRsCustomDataDetail.setStatus("SUCCESS");
        freezeAccountRsCustomData.setData(freezeAccountRsCustomDataDetail);
        freezeAccountRs.setFreezeAccountRsCustomData(freezeAccountRsCustomData);
        serviceResponse.setData(freezeAccountRs);
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ);
        StepVerifier.create(response).expectNextMatches(responseService -> responseService.getStatus()).verifyComplete();
    }

    @Test
    public void callFinacleFreezeAccountReturnFailedMockito(){
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        FinacleResponse serviceResponse = new FinacleResponse();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs();
        FreezeAccountRsCustomData freezeAccountRsCustomData = new FreezeAccountRsCustomData();
        FreezeAccountRsCustomDataDetail freezeAccountRsCustomDataDetail = new FreezeAccountRsCustomDataDetail();
        freezeAccountRsCustomDataDetail.setMessage("NO SE PUDO CONGELAR LA CUENTA");
        freezeAccountRsCustomDataDetail.setStatus("ERROR");
        freezeAccountRsCustomData.setData(freezeAccountRsCustomDataDetail);
        freezeAccountRs.setFreezeAccountRsCustomData(freezeAccountRsCustomData);
        serviceResponse.setData(freezeAccountRs);
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ);
        StepVerifier.create(response).expectNextMatches(responseService -> !responseService.getStatus()).verifyComplete();
    }

    @Test
    public void callFinacleFreezeAccountReturn5xxErrorMockito(){
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        WebClient webClient = WebClient.builder()
                .exchangeFunction(clientRequest ->
                        Mono.just(ClientResponse.create(HttpStatus.INTERNAL_SERVER_ERROR)
                                .header("content-type", "application/json")
                                .build())
                ).build();
        when(webClientMock.post()).thenReturn(webClient.post());
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ);
        StepVerifier.create(response).expectErrorMessage("Error comunicacion finacle").verify();
    }

    @Test
    public void callFinacleFreezeAccountReturnExceptionMockito(){
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ);
        StepVerifier.create(response).expectError(AccountOperationException.class).verify();
    }

    @Test
    public void callFinacleUnFreezeAccountReturnSuccess(){
        FinacleResponse serviceResponse = new FinacleResponse();
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        UnFreezeAccountRs unFreezeAccountRs = new UnFreezeAccountRs();
        unFreezeAccountRs.setData("SUCCESS");
        serviceResponse.setData(unFreezeAccountRs);
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("V1.0/banks/2/savings/accounts/00325652222/freeze")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(unFreezeAccountRq)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<UnFreezeAccountRsService> response =  accountServiceImpl.unFreezeAccount(unFreezeAccountRq);
        StepVerifier.create(response).expectNextMatches(responseService ->responseService.getMessage().equals("SUCCESS") ).verifyComplete();
    }

    @Test
    public void callFinacleUnFreeezeAccountReturnFailedMockito(){
        FinacleResponse serviceResponse = new FinacleResponse();
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        UnFreezeAccountRs unFreezeAccountRs = new UnFreezeAccountRs();
        unFreezeAccountRs.setData("NO SE PUDO DESCONGELAR LA CUENTA");
        serviceResponse.setData(unFreezeAccountRs);
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("V1.0/banks/2/savings/accounts/00325652222/freeze")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(unFreezeAccountRq)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<UnFreezeAccountRsService> response =  accountServiceImpl.unFreezeAccount(unFreezeAccountRq);
        StepVerifier.create(response).expectNextMatches(responseService ->responseService.getMessage().equals("NO SE PUDO DESCONGELAR LA CUENTA") ).verifyComplete();
    }

    @Test
    public void callFinacleUnFreezeAccountReturnException(){
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("V1.0/banks/2/savings/accounts/00325652222/freeze")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(unFreezeAccountRq)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        Mono<UnFreezeAccountRsService> response =  accountServiceImpl.unFreezeAccount(unFreezeAccountRq);
        StepVerifier.create(response).expectError(AccountOperationException.class).verify();
    }

    @Test
    public void callFinacleUnFreezeAccountReturn5xxErrorMockito(){
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        WebClient webClient = WebClient.builder()
                .exchangeFunction(clientRequest ->
                        Mono.just(ClientResponse.create(HttpStatus.INTERNAL_SERVER_ERROR)
                                .header("content-type", "application/json")
                                .build())
                ).build();
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(webClient.method(HttpMethod.DELETE));
        when(requestBodyUriSpecMock.uri("V1.0/banks/2/savings/accounts/00325652222/freeze")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(unFreezeAccountRq)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mono<UnFreezeAccountRsService> response =  accountServiceImpl.unFreezeAccount(unFreezeAccountRq);
        StepVerifier.create(response).expectErrorMessage("Error comunicacion finacle").verify();
    }

}
