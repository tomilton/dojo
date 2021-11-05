package co.com.nequi.usecase.getcustomerdetail;

import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCustomerDetailUseCase {

    public Mono<ResponseMdw> getCustomerDetail(RequestMdw requestMdw){
        ResponseMdw responseMdw;
        CustomerDetailReq customerDetailReq = (CustomerDetailReq) requestMdw.getRequestHeaderOut().getBody().getAny();
        /**
         * Se construye el HEADER
         */
        try{
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                    Constant.COMMON_STRING_SUCCESS, "", "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, customerDetailReq);
            responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
            return Mono.just(responseMdw);
        } catch(CreateCustomerException runtimeException){
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
