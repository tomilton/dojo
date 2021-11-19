package co.com.nequi.api.customer.datos;


import co.com.nequi.model.customer.LiteRegistryBrokerRS;
import co.com.nequi.model.responsemdw.*;

public class DatosResponseMdw {

    private DatosResponseMdw() {
    }

    public static Destination buildDestination() {
        Destination destination = Destination.builder().build();
        destination.setName("Customer");
        destination.setNamespace("http://co.bancaDigital/nequi/services/UserServices/Customer/v1.0");
        destination.setOperation("createCustomer");
        return destination;
    }

    public static Header buildHeader() {
        Header header = Header.builder().build();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setDestination(buildDestination());
        header.setResponseStatus(buildResponseStatus());
        return header;
    }

    public static ResponseStatus buildResponseStatus() {
        ResponseStatus responseStatus = ResponseStatus.builder().build();
        responseStatus.setStatusCode("0");
        responseStatus.setStatusDesc("SUCCESS");
        responseStatus.setErrorMessage("");
        responseStatus.setSystem("");
        return responseStatus;
    }

    public static LiteRegistryBrokerRS buildLiteRegistryBrokerRS() {
        LiteRegistryBrokerRS liteRegistryBrokerRS = new LiteRegistryBrokerRS();
        liteRegistryBrokerRS.setCifId("RET0000167");
        return liteRegistryBrokerRS;
    }

    public static Body buildBody() {
        Body body = Body.builder().build();
        body.setAny(buildLiteRegistryBrokerRS());
        return body;
    }

    public static ResponseHeaderOut buildResponseHeaderOut() {
        ResponseHeaderOut responseHeaderOut = ResponseHeaderOut.builder().build();
        responseHeaderOut.setHeader(buildHeader());
        responseHeaderOut.setBody(buildBody());
        return responseHeaderOut;
    }

    public static ResponseMdw buildResponseMdw() {
        ResponseMdw responseMdw = ResponseMdw.builder().build();
        responseMdw.setResponseHeaderOut(buildResponseHeaderOut());
        responseMdw.setOmitXMLDeclaration("yes");
        return responseMdw;
    }


}
