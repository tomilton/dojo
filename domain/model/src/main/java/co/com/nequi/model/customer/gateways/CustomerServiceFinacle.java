package co.com.nequi.model.customer.gateways;

import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CustomerServiceFinacle {

    Mono<CustomerResponseFinacle> save(CustomerRequestFinacle customerRequestFinacle);

}
