package co.com.nequi.model.customerdefaultdata.gateways;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import reactor.core.publisher.Flux;

public interface CustomerDefaultDataRepository {

    Flux<CustomerDefaultData> getDefaultData(String idServicio);

}
