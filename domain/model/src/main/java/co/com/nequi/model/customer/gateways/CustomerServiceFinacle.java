package co.com.nequi.model.customer.gateways;

import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.requestfinacle.customer.CustomerRequestFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle2;
import co.com.nequi.model.responsefinacle.detailprueba.CustomerDetailResponse;
import reactor.core.publisher.Mono;

public interface CustomerServiceFinacle {

    Mono<CustomerResponseFinacle> save(CustomerRequestFinacle customerRequestFinacle);

    Mono<CustomerDetailResponse> getCustomerDetail(CustomerDetailReq customerDetail);

    Mono<CustomerResponseFinacle2> getLock(String bankId, String idAccount);
}
