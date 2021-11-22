package co.com.nequi.nequiservice.account;

import co.com.nequi.model.account.dto.*;
import co.com.nequi.nequiservice.account.dto.*;
import co.com.nequi.model.exceptions.AccountOperationException;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;

import static co.com.nequi.nequiservice.util.Constants.SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestPropertySource(properties = {"finacle.uri.freezeAccount=/V1/banks/{bankId}/savings/FreezeAccount",
        "finacle.uri.unfreezeAccount=1.0/banks/{bankId}/savings/accounts/{account}/freeze"})
@SpringBootConfiguration
@ComponentScan({"co.com.nequi.nequiservice.configuration"})
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
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
        accountServiceImpl.uriFreezeAccount = "/V1/banks/{bankId}/savings/FreezeAccount";
        String bankId = "1600";
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        FinacleResponse serviceResponse = new FinacleResponse();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs();
        FreezeAccountRsCustomData freezeAccountRsCustomData = new FreezeAccountRsCustomData();
        FreezeAccountRsCustomDataMock freezeAccountRsCustomDataMock = new FreezeAccountRsCustomDataMock();
        FreezeAccountRsCustomDataDetail freezeAccountRsCustomDataDetail = new FreezeAccountRsCustomDataDetail();
        freezeAccountRsCustomDataDetail.setMessage(SUCCESS);
        freezeAccountRsCustomDataDetail.setStatus(SUCCESS);
        freezeAccountRsCustomData.setData(freezeAccountRsCustomDataDetail);
        freezeAccountRsCustomDataMock.setFreezeAccountRs_Customdata(freezeAccountRsCustomData);
        freezeAccountRs.setFreezeAccountRsCustomData(freezeAccountRsCustomData);
        serviceResponse.setData(freezeAccountRsCustomDataMock);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(mapper.map(any(),eq(FreezeAccountRsCustomDataMock.class))).thenReturn(freezeAccountRsCustomDataMock);
        accountServiceImpl.mapper = mapper;
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/{bankId}/savings/FreezeAccount",bankId)).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ,bankId);
        StepVerifier.create(response).expectNextMatches(responseService -> responseService.getStatus()).verifyComplete();
    }

    @Test
    public void callFinacleFreezeAccountReturnFailedMockito(){
        accountServiceImpl.uriFreezeAccount = "/V1/banks/{bankId}/savings/FreezeAccount";
        String bankId = "1600";
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        FinacleResponse serviceResponse = new FinacleResponse();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs();
        FreezeAccountRsCustomData freezeAccountRsCustomData = new FreezeAccountRsCustomData();
        FreezeAccountRsCustomDataMock freezeAccountRsCustomDataMock = new FreezeAccountRsCustomDataMock();
        FreezeAccountRsCustomDataDetail freezeAccountRsCustomDataDetail = new FreezeAccountRsCustomDataDetail();
        freezeAccountRsCustomDataDetail.setMessage("NO SE PUDO CONGELAR LA CUENTA");
        freezeAccountRsCustomDataDetail.setStatus("ERROR");
        freezeAccountRsCustomData.setData(freezeAccountRsCustomDataDetail);
        freezeAccountRs.setFreezeAccountRsCustomData(freezeAccountRsCustomData);
        freezeAccountRsCustomDataMock.setFreezeAccountRs_Customdata(freezeAccountRsCustomData);
        serviceResponse.setData(freezeAccountRsCustomDataMock);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(mapper.map(any(),eq(FreezeAccountRsCustomDataMock.class))).thenReturn(freezeAccountRsCustomDataMock);
        accountServiceImpl.mapper = mapper;
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/{bankId}/savings/FreezeAccount",bankId)).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ,bankId);
        StepVerifier.create(response).expectNextMatches(responseService -> !responseService.getStatus()).verifyComplete();
    }

    @Test
    public void callFinacleFreezeAccountReturn5xxErrorMockito(){
        accountServiceImpl.uriFreezeAccount = "/V1/banks/{bankId}/savings/FreezeAccount";
        String bankId = "1600";
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
        when(requestBodyUriSpecMock.uri("/V1/banks/{bankId}/savings/FreezeAccount",bankId)).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ,bankId);
        StepVerifier.create(response).expectErrorMessage("Error comunicacion finacle").verify();
    }

    @Test
    public void callFinacleFreezeAccountReturnExceptionMockito(){
        accountServiceImpl.uriFreezeAccount = "/V1/banks/{bankId}/savings/FreezeAccount";
        String bankId = "1600";
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/{bankId}/savings/FreezeAccount",bankId)).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(freezeAccountRQ)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        Mono<FreezeAccountRsService> response = accountServiceImpl.freezeAccount(freezeAccountRQ,bankId);
        StepVerifier.create(response).expectError(AccountOperationException.class).verify();
    }

    @Test
    public void callFinacleUnFreezeAccountReturnSuccess(){
        accountServiceImpl.uriUnFreezeAccount = "/1.0/banks/{bankId}/savings/accounts/{account}/freeze";
        FinacleResponse serviceResponse = new FinacleResponse();
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        UnFreezeAccountRsMockData unFreezeAccountRsMockData = new UnFreezeAccountRsMockData();
        UnFreezeAccountRsMock unFreezeAccountRsMock = new UnFreezeAccountRsMock();
        UnFreezeAccountRs unFreezeAccountRs = new UnFreezeAccountRs();
        unFreezeAccountRs.setData("SUCCESS");
        unFreezeAccountRsMock.setAccountUnFreezeRs(unFreezeAccountRs);
        unFreezeAccountRsMockData.setData(unFreezeAccountRsMock);
        serviceResponse.setData(unFreezeAccountRsMockData);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(mapper.map(any(),eq(UnFreezeAccountRsMockData.class))).thenReturn(unFreezeAccountRsMockData);
        accountServiceImpl.mapper = mapper;
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/1.0/banks/{bankId}/savings/accounts/{account}/freeze","2","00325652222")).thenReturn(requestBodySpecMock);
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
        accountServiceImpl.uriUnFreezeAccount = "/1.0/banks/{bankId}/savings/accounts/{account}/freeze";
        FinacleResponse serviceResponse = new FinacleResponse();
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        UnFreezeAccountRsMockData unFreezeAccountRsMockData = new UnFreezeAccountRsMockData();
        UnFreezeAccountRsMock unFreezeAccountRsMock = new UnFreezeAccountRsMock();
        UnFreezeAccountRs unFreezeAccountRs = new UnFreezeAccountRs();
        unFreezeAccountRs.setData("NO SE PUDO DESCONGELAR LA CUENTA");
        unFreezeAccountRsMock.setAccountUnFreezeRs(unFreezeAccountRs);
        unFreezeAccountRsMockData.setData(unFreezeAccountRsMock);
        serviceResponse.setData(unFreezeAccountRsMockData);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(mapper.map(any(),eq(UnFreezeAccountRsMockData.class))).thenReturn(unFreezeAccountRsMockData);
        accountServiceImpl.mapper = mapper;
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/1.0/banks/{bankId}/savings/accounts/{account}/freeze","2","00325652222")).thenReturn(requestBodySpecMock);
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
        accountServiceImpl.uriUnFreezeAccount = "/1.0/banks/{bankId}/savings/accounts/{account}/freeze";
        UnFreezeAccountRq unFreezeAccountRq = new UnFreezeAccountRq();
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new  UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unFreezeAccountRq.setAccountId("00325652222");
        unFreezeAccountRq.setBankId("2");
        unFreezeAccountRq.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        when(webClientMock.method(HttpMethod.DELETE)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/1.0/banks/{bankId}/savings/accounts/{account}/freeze","2","00325652222")).thenReturn(requestBodySpecMock);
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
        accountServiceImpl.uriUnFreezeAccount = "/1.0/banks/{bankId}/savings/accounts/{account}/freeze";
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
        when(requestBodyUriSpecMock.uri("/1.0/banks/{bankId}/savings/accounts/{account}/freeze","2","00325652222")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.header(any(),any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.accept(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.contentType(Mockito.any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.bodyValue(unFreezeAccountRq)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mono<UnFreezeAccountRsService> response =  accountServiceImpl.unFreezeAccount(unFreezeAccountRq);
        StepVerifier.create(response).expectErrorMessage("Error comunicacion finacle").verify();
    }

}
