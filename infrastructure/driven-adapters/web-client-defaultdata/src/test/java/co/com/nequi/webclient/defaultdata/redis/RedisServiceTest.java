package co.com.nequi.webclient.defaultdata.redis;

import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.redis.config.RedisConfig;
import co.com.nequi.redis.template.ReactiveRedisTemplateAdapter;
import co.com.nequi.webclient.defaultdata.DataProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootConfiguration
@SpringBootTest
@ComponentScan({"co.com.nequi.redis.config"})
@ContextConfiguration(classes = RedisConfig.class)
public class RedisServiceTest {

    private ReactiveRedisConnectionFactory reactiveRedisConnectionFactory = mock(ReactiveRedisConnectionFactory.class);

    private ObjectMapper objectMapper = mock(ObjectMapper.class);

    @Mock
    RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations valueOperations;

    private Set<String> keys = new HashSet<>();

    @Mock
    public ReactiveRedisTemplateAdapter redisTemplateAdapter = new ReactiveRedisTemplateAdapter(reactiveRedisConnectionFactory,objectMapper);

    @InjectMocks
    public RedisServiceImpl redisService;

    @Test
    public void getCustomerDefaultDataIdReturnOk(){
        Integer defaultDataBankId = Integer.valueOf("157");
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(redisTemplateAdapter.findById("157")).thenReturn(Mono.just(DataProvider.customerDefaultDataBank()));
        Mono<CustomerDefaultData> response = this.redisService.getCustomerDefaultDataId(defaultDataBankId);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),defaultDataBankId);
                    assertEquals(responseParam.getNombre(),"bankId");
                }).verifyComplete();

    }

    @Test
    public void getCustomerDefaultDataAllReturnOk(){
        String servicioId = "1";
        when(redisTemplate.keys("*")).thenReturn(keys);
        Flux<CustomerDefaultData> customerDefaultDataFlux = Flux.just(DataProvider.customerDefaultDataBank(),DataProvider.customerDefaultDataCustStatus());
        when(redisTemplateAdapter.findAll()).thenReturn(customerDefaultDataFlux);
        Flux<CustomerDefaultData> response = this.redisService.getCustomerDefaultDataAll(servicioId);
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
    public void saveDefaultPropertiesOk(){
        List<CustomerDefaultData> customerDefaultDataList = new ArrayList<>();
        customerDefaultDataList.add(DataProvider.customerDefaultDataBank());
        customerDefaultDataList.add(DataProvider.customerDefaultDataCustStatus());
        when(redisTemplateAdapter.save(eq(customerDefaultDataList.get(0).getDatoDefectoId().toString()),any())).thenReturn(Mono.just(DataProvider.customerDefaultDataBank()));
        when(redisTemplateAdapter.save(eq(customerDefaultDataList.get(1).getDatoDefectoId().toString()),any())).thenReturn(Mono.just(DataProvider.customerDefaultDataCustStatus()));
        this.redisService.saveDefaultProperties(customerDefaultDataList);
        verify(redisTemplateAdapter, times(1)).save(eq(customerDefaultDataList.get(0).getDatoDefectoId().toString()),any());
        verify(redisTemplateAdapter, times(1)).save(eq(customerDefaultDataList.get(1).getDatoDefectoId().toString()),any());
    }

}
