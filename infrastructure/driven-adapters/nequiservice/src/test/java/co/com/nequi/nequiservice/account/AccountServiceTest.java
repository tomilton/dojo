package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomData;
import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomDataDetail;
import co.com.nequi.model.exceptions.AccountOperationException;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
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
        HttpResponseStatus httpResponseStatus =  HttpResponseStatus.INTERNAL_SERVER_ERROR;
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
}