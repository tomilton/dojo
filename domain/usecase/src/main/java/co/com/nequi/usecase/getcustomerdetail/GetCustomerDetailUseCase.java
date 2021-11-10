package co.com.nequi.usecase.getcustomerdetail;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.CustomerDetailReq;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customer.gateways.LoggerCustomer;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.customer.*;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetCustomerDetailUseCase {

    private final CustomerServiceFinacle customerServiceFinacle;

    private final LoggerCustomer loggerCustomer;

    public Mono<ResponseMdw> getCustomerDetail(RequestMdw requestMdw){
        try{
            CustomerDetailReq customerDetailReq = (CustomerDetailReq) requestMdw.getRequestHeaderOut().getBody().getAny();
            Mono<CustomerDetailResponseFinacle> responseFinacleMono =customerServiceFinacle.getCustomerDetail(customerDetailReq);

            return responseFinacleMono
                    .onErrorResume(e -> Mono.just(buildErrorFinacle("", e.getMessage())))
                    .flatMap(finacle -> {
                        loggerCustomer.info("finacle: " + finacle);;
                        List<ErrorDetail> errorDetails = finacle.getMeta().getErrorDetails();
                        if(errorDetails.isEmpty()){
                            return Mono.empty();
                        } else {
                            return Mono.just(buildResponseSucces(finacle.getData().getData().getInquireDetailsRsCustomdata().getData().getOutputData().getData(), requestMdw));
                        }
                    });
        } catch (CreateCustomerException runtimeException) {
            return Mono.just(this.buildResponseWithError(requestMdw, runtimeException.getMessage()));
        } catch (Exception exception) {
            return Mono.just(this.buildResponseWithError(requestMdw, exception.getMessage()));
        }
    }

    private ResponseMdw buildResponseSucces(OutputDataData customer, RequestMdw requestMdw) {
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
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
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

    private CustomerDetailResponseFinacle buildErrorFinacle(String errorcode, String errordesc) {
        CustomerDetailResponseFinacle customerResponseFinacle = new CustomerDetailResponseFinacle();
        customerResponseFinacle.setMeta(new Meta());
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorcode(errorcode);
        errorDetail.setErrordesc(errordesc);
        customerResponseFinacle.getMeta().setErrorDetails(new ArrayList<>());
        customerResponseFinacle.getMeta().getErrorDetails().add(errorDetail);
        return customerResponseFinacle;
    }

}
