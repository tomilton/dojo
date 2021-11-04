package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

public class ResponseJsonMdw {
    private ResponseHeaderOut responseHeaderOut;
    private String omitXMLDeclaration;

    @JsonProperty("responseHeaderOut")
    public ResponseHeaderOut getResponseHeaderOut() { return responseHeaderOut; }
    @JsonProperty("responseHeaderOut")
    public void setResponseHeaderOut(ResponseHeaderOut value) { this.responseHeaderOut = value; }

    @JsonProperty("#omit-xml-declaration")
    public String getOmitXMLDeclaration() { return omitXMLDeclaration; }
    @JsonProperty("#omit-xml-declaration")
    public void setOmitXMLDeclaration(String value) { this.omitXMLDeclaration = value; }
}
