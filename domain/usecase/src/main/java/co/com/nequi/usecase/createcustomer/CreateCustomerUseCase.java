package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.LiteRegistryBrokerRQ;
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

        Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();

        return Mono.just(addResponse(customer));
    }

    public ResponseMdw addResponse(Object any) {
        ResponseMdw responseMdw = new ResponseMdw();
        Header headerRS = new Header();
        headerRS.setSystemID("MF-001");
        headerRS.setMessageID("42111635389666196");
        headerRS.setInvokerDateTime("2021-10-27 21:54:26");
        ResponseHeaderOut responseHeaderOut = new ResponseHeaderOut();
        responseHeaderOut.setHeader(headerRS);
        responseMdw.setResponseHeaderOut(responseHeaderOut);
        responseMdw.getResponseHeaderOut().setBody(new Body());
        responseMdw.getResponseHeaderOut().getBody().setAny(any);
        return responseMdw;
    }


}
