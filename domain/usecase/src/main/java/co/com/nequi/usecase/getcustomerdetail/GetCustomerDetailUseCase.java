package co.com.nequi.usecase.getcustomerdetail;

import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCustomerDetailUseCase {

    public Mono<ResponseMdw> getCustomerDetail(RequestMdw requestMdw){
        //CustomerDetailReq customerDetailReq = (CustomerDetailReq) requestMdw.getRequestHeaderOut().getBody().getAny();
        return Mono.just(addResponse(requestMdw));
    }

    public ResponseMdw addResponse(Object any){
        ResponseMdw responseMdw = new ResponseMdw();
        Header headerRS = new Header();
        headerRS.setSystemID("MF-001");
        headerRS.setMessageID("42111635389666196");
        headerRS.setInvokerDateTime("2021-10-27 21:54:26");

        Destination destination = new Destination();
        destination.setName("CustomerDetails");
        destination.setNamespace("http://co.bancaDigital/nequi/services/UserServices/CustomerDetails/v1.0");
        destination.setOperation("getCustomerStatus");
        headerRS.setDestination(destination);

        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode("0");
        responseStatus.setStatusDesc("SUCCESS");
        responseStatus.setErrorMessage("");
        responseStatus.setSystem("");
        headerRS.setResponseStatus(responseStatus);

        ResponseHeaderOut responseHeaderOut = new ResponseHeaderOut();
        responseHeaderOut.setHeader(headerRS);

        responseMdw.setResponseHeaderOut(responseHeaderOut);
        responseMdw.getResponseHeaderOut().setBody(new Body());
        responseMdw.getResponseHeaderOut().getBody().setAny(any);
        return responseMdw;
    }
}
