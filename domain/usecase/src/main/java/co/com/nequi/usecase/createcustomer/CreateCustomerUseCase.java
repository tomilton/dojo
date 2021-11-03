package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.LiteRegistryBrokerRQ;
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
            return Mono.just(addResponse(customer));

        } catch (CreateCustomerException runtimeException) {
            throw runtimeException;
        }
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
