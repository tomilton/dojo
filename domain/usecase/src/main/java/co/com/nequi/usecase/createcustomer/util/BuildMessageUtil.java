package co.com.nequi.usecase.createcustomer.util;

import co.com.nequi.model.responsemdw.*;

public class BuildMessageUtil {

    private BuildMessageUtil() {
    }

    public static Destination buildDestination(String name, String namespace, String operation) {
        return Destination
                .builder()
                .name(name)
                .namespace(namespace)
                .operation(operation)
                .build();
    }

    public static Header buildHeader(
            String systemID,
            String messageID,
            String invokerDateTime,
            Destination destination,
            ResponseStatus responseStatus) {
        return Header
                .builder()
                .systemID(systemID)
                .messageID(messageID)
                .invokerDateTime(invokerDateTime)
                .destination(destination)
                .responseStatus(responseStatus)
                .build();
    }

    public static ResponseStatus buildStatus(String statusCode, String statusDesc, String errorMessage,
                                             String system) {
        return ResponseStatus
                .builder()
                .statusCode(statusCode)
                .statusDesc(statusDesc)
                .errorMessage(errorMessage)
                .system(system)
                .build();
    }

    public static ResponseHeaderOut buildResponseHeaderOut(Header header, Object any) {
        return ResponseHeaderOut
                .builder()
                .header(header)
                .body(Body.builder().any(any).build())
                .build();
    }

    public static ResponseMdw buildResponse(ResponseHeaderOut responseHeaderOut, String omitXMLDeclaration) {
        return ResponseMdw.builder()
                .responseHeaderOut(responseHeaderOut)
                .omitXMLDeclaration(omitXMLDeclaration)
                .build();
    }

}
