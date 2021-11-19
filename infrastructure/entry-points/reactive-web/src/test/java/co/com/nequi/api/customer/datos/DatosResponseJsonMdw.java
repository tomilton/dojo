package co.com.nequi.api.customer.datos;


import co.com.nequi.api.responsemdw.*;

public class DatosResponseJsonMdw {

    private DatosResponseJsonMdw() {
    }

    public static Destination buildDestination() {
        Destination destination = new Destination();
        destination.setName("Customer");
        destination.setNamespace("http://co.bancaDigital/nequi/services/UserServices/Customer/v1.0");
        destination.setOperation("createCustomer");
        return destination;
    }

    public static Header buildHeader() {
        Header header = new Header();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setDestination(buildDestination());
        header.setResponseStatus(buildResponseStatus());
        return header;
    }

    public static ResponseStatus buildResponseStatus() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode("0");
        responseStatus.setStatusDesc("SUCCESS");
        responseStatus.setErrorMessage("");
        responseStatus.setSystem("");
        return responseStatus;
    }

    public static LiteRegistryBrokerRS buildLiteRegistryBrokerRS() {
        LiteRegistryBrokerRS liteRegistryBrokerRS = new LiteRegistryBrokerRS();
        liteRegistryBrokerRS.setCifID("RET0000167");
        return liteRegistryBrokerRS;
    }

    public static Body buildBody() {
        Body body = new Body();
        body.setAny(buildLiteRegistryBrokerRS());
        return body;
    }

    public static ResponseHeaderOut buildResponseHeaderOut() {
        ResponseHeaderOut responseHeaderOut = new ResponseHeaderOut();
        responseHeaderOut.setHeader(buildHeader());
        responseHeaderOut.setBody(buildBody());
        return responseHeaderOut;
    }

    public static ResponseJsonMdw buildResponseJsonMdw() {
        ResponseJsonMdw responseJsonMdw = new ResponseJsonMdw();
        responseJsonMdw.setResponseHeaderOut(buildResponseHeaderOut());
        responseJsonMdw.setOmitXMLDeclaration("yes");
        return responseJsonMdw;
    }


}
