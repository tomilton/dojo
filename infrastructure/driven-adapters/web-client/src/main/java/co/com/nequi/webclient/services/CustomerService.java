package co.com.nequi.webclient.services;


import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customer.gateways.LoggerCustomer;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.webclient.json.customer.request.CustomerRequestJSON;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CustomerService implements CustomerServiceFinacle, LoggerCustomer {

    @Autowired
    private WebClient.Builder client;

    private final ObjectMapper objectMapper = new ObjectMapperImp();

    @Override
    public Mono<CustomerResponseFinacle> save(CustomerRequestFinacle customerRequestFinacle) {

        CustomerRequestJSON requestJSON = objectMapper.map(customerRequestFinacle, CustomerRequestJSON.class);

        Mono<CustomerResponseJSON> finacleResponse = client.build().post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new CreateCustomerFinacleException("Error comunicación finacle")))
                .bodyToMono(CustomerResponseJSON.class)
                .onErrorMap(throwable -> new CreateCustomerFinacleException(throwable.getMessage()));

        return finacleResponse.map(obj -> objectMapper.map(obj, CustomerResponseFinacle.class));


    }

    @Override
    public void info(String info) {
        Logger logger = LoggerFactory.getLogger(CustomerService.class);
        logger.info(info);
    }
}
