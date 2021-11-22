package co.com.nequi.model.oracle;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RedisService {
    Mono<CustomerDefaultData> getCustomerDefaultDataId(Integer defaultDataId);
    Flux<CustomerDefaultData> getCustomerDefaultDataAll(String servicioId);
    void saveDefaultProperties(List<CustomerDefaultData> customerDefaultDataList);
}