package co.com.nequi.webclient.defaultdata.config;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.exceptions.DefaultDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ServiceDefaultData implements CustomerDefaultDataRepository {

    @Autowired
    private WebClient.Builder client;

    @Override
    public Flux<CustomerDefaultData> getDefaultData(String idServicio) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", idServicio);
        return client.build().get().uri("/api/datodefecto/{id}", params)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CustomerDefaultData.class)
                .onErrorMap(throwable -> new DefaultDataException(throwable.getMessage()));
    }
}
