package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.Body;
import co.com.nequi.model.responsemdw.Header;
import co.com.nequi.model.responsemdw.ResponseHeaderOut;
import co.com.nequi.model.responsemdw.ResponseMdw;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    public Mono<ResponseMdw> createCustomer(RequestMdw requestMdw) {
        try {

            Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();
            customer.getLiteRegistryBrokerRQ().getPersonalInfo().validarIdNumber();
            return Mono.just(this.buildResponseMdw(customer));

        } catch (CreateCustomerException runtimeException) {
            throw runtimeException;
        }
    }

    public ResponseMdw buildResponseMdw(Object any) {
        Header headerRS = Header
                .builder()
                .systemID("MF-001")
                .messageID("42111635389666196")
                .invokerDateTime("2021-10-27 21:54:26")
                .build();
        ResponseHeaderOut responseHeaderOut = ResponseHeaderOut
                .builder()
                .header(headerRS)
                .body(Body.builder().any(any).build())
                .build();
        return ResponseMdw.builder()
                .responseHeaderOut(responseHeaderOut)
                .build();

    }


}
