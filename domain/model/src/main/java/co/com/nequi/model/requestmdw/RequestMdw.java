package co.com.nequi.model.requestmdw;

public class RequestMdw {
    private RequestHeaderOut requestHeaderOut;
    private String omitXMLDeclaration;

    public RequestHeaderOut getRequestHeaderOut() {
        return requestHeaderOut;
    }

    public void setRequestHeaderOut(RequestHeaderOut value) {
        this.requestHeaderOut = value;
    }

    public String getOmitXMLDeclaration() {
        return omitXMLDeclaration;
    }

    public void setOmitXMLDeclaration(String value) {
        this.omitXMLDeclaration = value;
    }
}
