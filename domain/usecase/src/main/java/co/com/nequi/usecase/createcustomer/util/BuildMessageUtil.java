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

    public static ResponseMdw buildResponseMdw(Object any) {
        Destination destination = Destination
                .builder()
                .name("CustomerDevice")
                .namespace("http://co.bancaDigital/nequi/services/SecurityServices/CustomerDevice/v1.0")
                .operation("createCustomer")
                .build();
        ResponseStatus responseStatus = ResponseStatus
                .builder()
                .statusCode("0")
                .statusDesc("SUCCESS")
                .errorMessage("")
                .system("")
                .build();
        Header headerRS = Header
                .builder()
                .systemID("MF-001")
                .messageID("42111635389666196")
                .invokerDateTime("2021-10-27 21:54:26")
                .destination(destination)
                .responseStatus(responseStatus)
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
