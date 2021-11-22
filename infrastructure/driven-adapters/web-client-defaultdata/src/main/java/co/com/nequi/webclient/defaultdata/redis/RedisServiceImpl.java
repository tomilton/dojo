package co.com.nequi.webclient.defaultdata.redis;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.oracle.RedisService;
import co.com.nequi.redis.template.ReactiveRedisTemplateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private ReactiveRedisTemplateAdapter redisTemplate;

    @Override
    public Mono<CustomerDefaultData> getCustomerDefaultDataId(Integer defaultDataId) {
        return redisTemplate.findById(defaultDataId.toString());
    }

    @Override
    public Flux<CustomerDefaultData> getCustomerDefaultDataAll(String servicioId) {
        Flux<CustomerDefaultData> getAll = redisTemplate.findAll();
        return getAll.filter((customerDefaultData) -> customerDefaultData.getServicioId().equals(servicioId));
    }

    @Override
    public void saveDefaultProperties(List<CustomerDefaultData> customerDefaultDataList) {
        for (CustomerDefaultData customerDefaultData : customerDefaultDataList) {
            redisTemplate.save(customerDefaultData.getDatoDefectoId().toString(),customerDefaultData).subscribe();
        }
    }
}
