package co.com.nequi.webclient.defaultdata.config;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.DefaultDataException;
import co.com.nequi.model.oracle.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ServiceDefaultData implements CustomerDefaultDataRepository {

    @Autowired
    private WebClient.Builder client;

    @Autowired
    private RedisService redisService;

    @Override
    public Flux<CustomerDefaultData> getDefaultData(String idServicio) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", idServicio);
        Flux<CustomerDefaultData> customerDefaultDataService = this.redisService.getCustomerDefaultDataAll(idServicio);
        return customerDefaultDataService.switchIfEmpty(saveDataCache(idServicio));
    }

    @Override
    public Mono<CustomerDefaultData> getDefaultDataId(Integer id,String idServicio) {
        Mono<CustomerDefaultData> defaultData = this.redisService.getCustomerDefaultDataId(id);
        return defaultData.switchIfEmpty(customerSaveDefaultDataMono(id,idServicio));
    }

    private Mono<CustomerDefaultData> customerSaveDefaultDataMono(Integer id,String idServicio){
        Flux<CustomerDefaultData> saveData = this.saveDataCache(idServicio);
        return saveData.filter(customerDefaultData -> customerDefaultData.getDatoDefectoId().equals(id)).next();
    }

    private Flux<CustomerDefaultData> saveDataCache(String servicioId){
        Map<String, Object> params = new HashMap<>();
        params.put("id", servicioId);
        Flux<CustomerDefaultData> dataBd = getDataBase(params);
        return dataBd.collectList().flatMapIterable(customerDefaultDataList -> {
            this.redisService.saveDefaultProperties(customerDefaultDataList);
            return customerDefaultDataList;
        });
    }

    private Flux<CustomerDefaultData> getDataBase(Map<String,Object> params){
        return client.build()
                .get()
                .uri("/api/datodefecto/{id}", params)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CustomerDefaultData.class)
                .onErrorMap(throwable -> new DefaultDataException(throwable.getMessage()));
    }
}
