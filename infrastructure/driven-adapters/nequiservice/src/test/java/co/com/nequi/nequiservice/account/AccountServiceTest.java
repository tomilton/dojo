package co.com.nequi.nequiservice.account;

import co.com.nequi.nequiservice.account.dto.FinacleResponse;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRs;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomData;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.nequiservice.account.dto.FreezeAccountRsCustomDataDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private WebTestClient webClient;
    private WebClient.RequestHeadersSpec requestHeadersMock;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void callFinacleFreezeAccountReturnSuccess(){
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
                .uri("https://14829b62-d02f-4cf5-b2a8-3c0b3b8f7884.mock.pstmn.io//V1/banks/1/savings/FreezeAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(freezeAccountRqDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(FinacleResponse.class)
                .consumeWith(re -> assertThat(re.getResponseBody().getData().getFreezeAccountRsCustomData().getData().getStatus()="SUCCESS").isEqualByComparingTo(re.getResponseBody()));
        webClient.post().accept(MediaType.APPLICATION_JSON).bodyValue(serviceResponse);
        Mono<Boolean> employeeMono = accountServiceImpl.freezeAccount(freezeAccountRqDto);
        StepVerifier.create(employeeMono).expectNext(Boolean.TRUE).verifyComplete();
    }
}
