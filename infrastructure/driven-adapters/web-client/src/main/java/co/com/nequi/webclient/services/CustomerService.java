package co.com.nequi.webclient.services;


import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.webclient.json.customer.request.CustomerRequestJSON;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CustomerService implements CustomerServiceFinacle {

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
                .bodyToMono(CustomerResponseJSON.class);

        return finacleResponse.map(obj -> objectMapper.map(obj, CustomerResponseFinacle.class));


    }
}
