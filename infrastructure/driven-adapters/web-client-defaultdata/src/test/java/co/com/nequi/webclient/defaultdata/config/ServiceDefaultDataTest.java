package co.com.nequi.webclient.defaultdata.config;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.oracle.RedisService;
import co.com.nequi.redis.config.RedisConfig;
import co.com.nequi.webclient.defaultdata.DataProvider;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootConfiguration
@SpringBootTest
@ComponentScan({"co.com.nequi.redis.config"})
@ContextConfiguration(classes = RedisConfig.class)
public class ServiceDefaultDataTest {

    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.Builder webClientBuilderMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private ServiceDefaultData serviceDefaultData;

    @Test
    public void getDefaultDataGetCacheAllOK(){
        String idServicio = "1";
        Flux<CustomerDefaultData> customerDefaultDataFlux = Flux.just(DataProvider.customerDefaultDataBank(),DataProvider.customerDefaultDataCustStatus());
        Map<String, Object> params = new HashMap<>();
        params.put("id", idServicio);
        when(redisService.getCustomerDefaultDataAll(eq(idServicio))).thenReturn(customerDefaultDataFlux);
        when(webClientBuilderMock.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq("/api/datodefecto/{id}"),eq(params))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.accept(Mockito.any())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(
                    ArgumentMatchers.<Class<CustomerDefaultData>>notNull())).thenReturn(customerDefaultDataFlux);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Flux<CustomerDefaultData> response = this.serviceDefaultData.getDefaultData(idServicio);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),Integer.valueOf("157"));
                    assertEquals(responseParam.getNombre(),"bankId");
                    assertEquals(responseParam.getValorDefecto(),"1600");
                }).consumeNextWith(responseParam2 -> {
                    assertEquals(responseParam2.getDatoDefectoId(),Integer.valueOf("1"));
                    assertEquals(responseParam2.getNombre(),"CustStatus");
                    assertEquals(responseParam2.getValorDefecto(),"ACTIVE");
                }).verifyComplete();
    }

    @Test
    public void getDefaultDataGetNoCacheAllOK(){
        String idServicio = "1";
        Flux<CustomerDefaultData> customerDefaultDataFlux = Flux.just(DataProvider.customerDefaultDataBank(),DataProvider.customerDefaultDataCustStatus());
        Map<String, Object> params = new HashMap<>();
        params.put("id", idServicio);
        when(redisService.getCustomerDefaultDataAll(eq(idServicio))).thenReturn(Flux.empty());
        when(webClientBuilderMock.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq("/api/datodefecto/{id}"),eq(params))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.accept(Mockito.any())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(
                ArgumentMatchers.<Class<CustomerDefaultData>>notNull())).thenReturn(customerDefaultDataFlux);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Flux<CustomerDefaultData> response = this.serviceDefaultData.getDefaultData(idServicio);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),Integer.valueOf("157"));
                    assertEquals(responseParam.getNombre(),"bankId");
                    assertEquals(responseParam.getValorDefecto(),"1600");
                }).consumeNextWith(responseParam2 -> {
                    assertEquals(responseParam2.getDatoDefectoId(),Integer.valueOf("1"));
                    assertEquals(responseParam2.getNombre(),"CustStatus");
                    assertEquals(responseParam2.getValorDefecto(),"ACTIVE");
                }).verifyComplete();
    }

    @Test
    public void getDefaultDataIdCacheOK(){
        String idServicio = "1";
        Integer idDataDefault = Integer.valueOf("157");
        Mono<CustomerDefaultData> customerDefaultData = Mono.just(DataProvider.customerDefaultDataBank());
        Flux<CustomerDefaultData> customerDefaultDataFlux = Flux.just(DataProvider.customerDefaultDataBank(),DataProvider.customerDefaultDataCustStatus());
        Map<String, Object> params = new HashMap<>();
        params.put("id", idServicio);
        when(redisService.getCustomerDefaultDataId(eq(idDataDefault))).thenReturn(customerDefaultData);
        when(webClientBuilderMock.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq("/api/datodefecto/{id}"),eq(params))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.accept(Mockito.any())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(
                ArgumentMatchers.<Class<CustomerDefaultData>>notNull())).thenReturn(customerDefaultDataFlux);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Mono<CustomerDefaultData> response = this.serviceDefaultData.getDefaultDataId(idDataDefault,idServicio);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),Integer.valueOf("157"));
                    assertEquals(responseParam.getNombre(),"bankId");
                    assertEquals(responseParam.getValorDefecto(),"1600");
                }).verifyComplete();
    }

    @Test
    public void getDefaultDataIdNoCacheOK(){
        String idServicio = "1";
        Integer idDataDefault = Integer.valueOf("157");
        Flux<CustomerDefaultData> customerDefaultDataFlux = Flux.just(DataProvider.customerDefaultDataBank(),DataProvider.customerDefaultDataCustStatus());
        Map<String, Object> params = new HashMap<>();
        params.put("id", idServicio);
        when(redisService.getCustomerDefaultDataId(eq(idDataDefault))).thenReturn(Mono.empty());
        when(webClientBuilderMock.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq("/api/datodefecto/{id}"),eq(params))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.accept(Mockito.any())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(
                ArgumentMatchers.<Class<CustomerDefaultData>>notNull())).thenReturn(customerDefaultDataFlux);
        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
        Mono<CustomerDefaultData> response = this.serviceDefaultData.getDefaultDataId(idDataDefault,idServicio);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),Integer.valueOf("157"));
                    assertEquals(responseParam.getNombre(),"bankId");
                    assertEquals(responseParam.getValorDefecto(),"1600");
                }).verifyComplete();
    }
}
