package co.com.nequi.nequiservice.account;

import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomData;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomDataDetail;
import co.com.nequi.nequiservice.configuration.AccountOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Mock
    private Mono<FreezeAccountRs> postResponseMockFinacleFreezeAccount;
    private WebClient.RequestHeadersSpec requestHeadersMock;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    //@Test
    /**public void callFinacleFreezeAccountReturnSuccess(){
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode(10);
        freezeAccountRqDto.setFreezeCode("D");
        FinacleResponse serviceResponse = new FinacleResponse();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs();
        FreezeAccountRsCustomData freezeAccountRsCustomData = new FreezeAccountRsCustomData();
        FreezeAccountRsCustomDataDetail freezeAccountRsCustomDataDetail = new FreezeAccountRsCustomDataDetail();
        freezeAccountRsCustomDataDetail.setMessage("SUCCESS");
        freezeAccountRsCustomDataDetail.setStatus("SUCCESS");
        freezeAccountRsCustomData.setData(freezeAccountRsCustomDataDetail);
        freezeAccountRs.setFreezeAccountRsCustomData(freezeAccountRsCustomData);
        serviceResponse.setData(freezeAccountRs);
        Mono<FinacleResponse> serviceMonoResponse = Mono.just(serviceResponse);
        webClient.post()
                .uri("https://14829b62-d02f-4cf5-b2a8-3c0b3b8f7884.mock.pstmn.io/V1/banks/1/savings/FreezeAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(freezeAccountRqDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(FinacleResponse.class)
                .consumeWith(finacleResponseEntityExchangeResult -> assertThat(finacleResponseEntityExchangeResult.getResponseBody().getData().getFreezeAccountRsCustomData().getData()).isEqualTo("SUCCESS"));
        webClient.post().accept(MediaType.APPLICATION_JSON).bodyValue(serviceResponse);
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectNext(Boolean.TRUE).verifyComplete();
    }*/

    @Test
    public void callFinacleFreezeAccountReturnSuccessMockito(){
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode(10);
        freezeAccountRqDto.setFreezeCode("D");
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
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectNext(Boolean.TRUE).verifyComplete();
        assertThat(employeeMono.block()).isTrue();
    }

    @Test
    public void callFinacleFreezeAccountReturnFailedMockito(){
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode(10);
        freezeAccountRqDto.setFreezeCode("D");
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
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.just(serviceResponse));
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectNext(Boolean.FALSE).verifyComplete();
        assertThat(employeeMono.block()).isFalse();
    }

    /**@Test
    public void callFinacleFreezeAccountReturn5xxErrorMockito(){
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode(10);
        freezeAccountRqDto.setFreezeCode("D");
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        //when(responseSpecMock.onStatus(HttpStatus::is5xxServerError)).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectError(AccountOperationException.class).verify();
    }*/

    @Test
    public void callFinacleFreezeAccountReturnExceptionMockito(){
        FreezeAccountRqDto freezeAccountRqDto = new FreezeAccountRqDto();
        freezeAccountRqDto.setAccountNumber("87052427983");
        freezeAccountRqDto.setReasonCode(10);
        freezeAccountRqDto.setFreezeCode("D");
        when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri("/V1/banks/1/savings/FreezeAccount")).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<FinacleResponse>>notNull())).thenReturn(Mono.error(new Exception("error call finacle")));
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectError(AccountOperationException.class).verify();
    }
}
