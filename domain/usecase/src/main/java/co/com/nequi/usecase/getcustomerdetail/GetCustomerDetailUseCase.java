package co.com.nequi.usecase.getcustomerdetail;

import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customer.gateways.LoggerCustomer;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.detailprueba.*;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetCustomerDetailUseCase {

    private final CustomerServiceFinacle customerServiceFinacle;

    private final LoggerCustomer loggerCustomer;

    public Mono<ResponseMdw> getCustomerDetail(RequestMdw requestMdw){

        CustomerDetailReq customerDetailReq = (CustomerDetailReq) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<CustomerDetailResponse> customerDetailResponseMono = customerServiceFinacle.getCustomerDetail(customerDetailReq);

        return customerDetailResponseMono
                .flatMap(response ->
                        Mono.just(buildResponseSucces(response, requestMdw)))
                .doOnError(e -> {
                    loggerCustomer.info("Error");
                })
                .onErrorResume(error -> handleErrors(error, requestMdw));
    }

    private ResponseMdw buildResponseSucces(CustomerDetailResponse customer, RequestMdw requestMdw) {
        List<ErrorDetail> errorDetails = customer.getMeta().getErrorDetails();
        if(errorDetails == null || errorDetails.isEmpty()){
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                    Constant.COMMON_STRING_SUCCESS, "", "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header,
                    customer.getData().getData().getInquireDetailsRsCustomdata().getOutputData().getData().getData());
            return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
        } else {
            return buildResponseWithError(requestMdw, errorDetails.toString());
        }
    }

    private Mono<ResponseMdw> handleErrors(Throwable error, RequestMdw requestMdw) {
        return Mono.just(this.buildResponseWithError(requestMdw, error.getMessage()));
    }

    private ResponseMdw buildResponseWithError(RequestMdw requestMdw, String error) {
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.ERROR_GENERIC_CODE,
                Constant.COMMON_STRING_ERROR_GENERIC, error, "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, "");
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
    }

}
