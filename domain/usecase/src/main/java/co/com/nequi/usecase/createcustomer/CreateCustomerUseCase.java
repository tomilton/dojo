package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    public Mono<ResponseMdw> createCustomer(RequestMdw requestMdw) {
        ResponseMdw responseMdw;
        try {
            Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();
            customer.getLiteRegistryBrokerRQ().getPersonalInfo().validarIdNumber();
            /**
             * Se construye el HEADER
             */
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                    Constant.COMMON_STRING_SUCCESS, "", "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, customer);
            responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
            return Mono.just(responseMdw);

        } catch (CreateCustomerException runtimeException) {
            /**
             * Se construye el HEADER con error
             */
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.ERROR_GENERIC_CODE,
                    Constant.COMMON_STRING_ERROR_GENERIC, runtimeException.getMessage(), "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, "");
            responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
            return Mono.just(responseMdw);
        }
    }


}
