package co.com.nequi.webclient.services;

import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customer.gateways.LoggerCustomer;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerDetailResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.Meta;
import co.com.nequi.webclient.json.customer.request.CustomerDetailRequestJSON;
import co.com.nequi.webclient.json.customer.request.CustomerRequestJSON;
import co.com.nequi.webclient.json.customer.request.GetCustomerDetails;
import co.com.nequi.webclient.json.customer.response.CustomerDetailResponseJSON;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import co.com.nequi.webclient.json.customer.response.FinacleResponse;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceFinacleImpl implements CustomerServiceFinacle, LoggerCustomer {

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

    @Override
    public Mono<CustomerDetailResponseFinacle> getCustomerDetail(CustomerDetailReq customerDetail) {
        CustomerDetailRequestJSON requestJSON = new CustomerDetailRequestJSON();

        GetCustomerDetails getCustomerDetails = new GetCustomerDetails();
        getCustomerDetails.setDocument(customerDetail.getGetCustomerStatusRQ().getDocument());
        getCustomerDetails.setPhoneNumber(customerDetail.getGetCustomerStatusRQ().getPhoneNumber());

        requestJSON.setGetCustomerDetails(getCustomerDetails);

        Mono<String> finacleResponse = client.post()
                .uri("/V1.0/banks/1500/savings/InquireDetails")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new Exception()))
                .bodyToMono(String.class)
                /*.onErrorResume(
                        Exception.class,
                        error -> {
                            info(error.getMessage());
                            return Mono.just(4);
                        }
                )*/
                .onErrorMap(t -> {
                    info(t.getMessage());
                    new Exception();
                    return t;
                });

                finacleResponse.doOnNext(f -> {
                    info(f.toString());
                }).subscribe();

        Mono<CustomerDetailResponseFinacle> custom = finacleResponse.map( f-> {
            info(f.toString());
            CustomerDetailResponseFinacle obj = objectMapper.map(f, CustomerDetailResponseFinacle.class);

            return obj;
        });

        //custom.doOnNext( c -> info(c.getData().getData().getInquireDetailsRsCustomdata().getData().getOutputData().getData().getAcctStatus()));

        return null;
        /*return finacleResponse.map(obj -> {
         CustomerDetailResponseFinacle customerDetailResponseFinacle = new CustomerDetailResponseFinacle();

         Meta meta = new Meta();
         meta.setContexturl(obj.getMeta().getContexturl());

         customerDetailResponseFinacle.setMeta(meta);
         return customerDetailResponseFinacle;
        });*/
    }

    @Override
    public void info(String info) {
        Logger logger = LoggerFactory.getLogger(CustomerServiceFinacleImpl.class);
        logger.info(info);
    }
}
