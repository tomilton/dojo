package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestJsonMdw {
    private RequestHeaderOut requestHeaderOut;
    private String omitXMLDeclaration;

    @JsonProperty("requestHeaderOut")
    public RequestHeaderOut getRequestHeaderOut() { return requestHeaderOut; }
    @JsonProperty("requestHeaderOut")
    public void setRequestHeaderOut(RequestHeaderOut value) { this.requestHeaderOut = value; }

    @JsonProperty("#omit-xml-declaration")
    public String getOmitXMLDeclaration() { return omitXMLDeclaration; }
    @JsonProperty("#omit-xml-declaration")
    public void setOmitXMLDeclaration(String value) { this.omitXMLDeclaration = value; }
}
