package co.com.nequi.processor.services.util;

import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.*;
import org.json.JSONObject;
import reactor.core.publisher.Mono;

public class ResponseUtil {
    public static ResponseMdw buildResponseSuccess(Object anyResponse, RequestMdw requestMdw){
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                Constant.COMMON_STRING_SUCCESS, "", "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, anyResponse);
        ResponseMdw responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
        return responseMdw;
    }

    public static Mono<ResponseMdw> handleErrors(Throwable error, RequestMdw requestMdw) {
        System.out.println("handle error control "+error);
        return Mono.just(buildResponseWithError(requestMdw, error.getMessage(),(error instanceof BusinessException)));
    }

    /**
     * Construye la respuesta con el error para notificarle a middleware
     *
     * @param requestMdw
     * @param error
     * @return
     */
    private static  ResponseMdw buildResponseWithError(RequestMdw requestMdw, String error,boolean error4xx) {
        System.out.println("es 422 error "+error4xx);
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus((error4xx ? Constant.ERROR_UNPROCESSABLE_ENTITY_CODE : Constant.ERROR_GENERIC_CODE),
                Constant.COMMON_STRING_ERROR_GENERIC, error, "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, "");
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
    }
}
