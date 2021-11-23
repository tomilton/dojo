package co.com.nequi.model.customerdefaultdata.gateways;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerDefaultDataRepository {

    Flux<CustomerDefaultData> getDefaultData(String idServicio);
    Mono<CustomerDefaultData> getDefaultDataId(Integer id,String idServicio);

}
