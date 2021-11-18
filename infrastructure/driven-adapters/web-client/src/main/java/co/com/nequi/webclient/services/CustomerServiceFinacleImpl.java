package co.com.nequi.webclient.services;

import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.webclient.json.customer.request.CustomerRequestJSON;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceFinacleImpl implements CustomerServiceFinacle {

    @Autowired
    private WebClient client;

    private final ObjectMapper objectMapper = new ObjectMapperImp();

    @Override
    public Mono<CustomerResponseFinacle> save(CustomerRequestFinacle customerRequestFinacle) {
        CustomerRequestJSON requestJSON = objectMapper.map(customerRequestFinacle, CustomerRequestJSON.class);
        Mono<CustomerResponseJSON> finacleResponse = client.post()
                .uri("/V1.0/banks/1500/cif/api/Retail/create")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new CreateCustomerFinacleException("Error comunicación finacle")))
                .bodyToMono(CustomerResponseJSON.class)
                .onErrorMap(throwable -> new CreateCustomerFinacleException(throwable.getMessage()));
        return finacleResponse.map(obj -> objectMapper.map(obj, CustomerResponseFinacle.class));
    }

}
