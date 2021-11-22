package co.com.nequi.webclient.defaultdata.redis;

import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.redis.config.RedisConfig;
import co.com.nequi.redis.template.ReactiveRedisTemplateAdapter;
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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Mock
    public ReactiveRedisTemplateAdapter redisTemplateAdapter = new ReactiveRedisTemplateAdapter(reactiveRedisConnectionFactory,objectMapper);

    @InjectMocks
    public RedisServiceImpl redisService;

    @Test
    public void getCustomerDefaultDataIdReturnOk(){
        Integer defaultDataBankId = Integer.valueOf("157");
        CustomerDefaultData customerDefaultData = new CustomerDefaultData();
        customerDefaultData.setNombre("bankId");
        customerDefaultData.setDatoDefectoId(defaultDataBankId);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(redisTemplateAdapter.findById("157")).thenReturn(Mono.just(customerDefaultData));
        Mono<CustomerDefaultData> response = this.redisService.getCustomerDefaultDataId(defaultDataBankId);
        StepVerifier.create(response)
                .consumeNextWith(responseParam -> {
                    assertNotNull(responseParam);
                    assertEquals(responseParam.getDatoDefectoId(),defaultDataBankId);
                    assertEquals(responseParam.getNombre(),"bankId");
                }).verifyComplete();

    }
}
