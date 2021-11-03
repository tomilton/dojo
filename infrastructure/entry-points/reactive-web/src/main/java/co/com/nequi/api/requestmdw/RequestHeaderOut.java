package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestHeaderOut {
    private Header header;
    private Body body;

    @JsonProperty("Header")
    public Header getHeader() { return header; }
    @JsonProperty("Header")
    public void setHeader(Header value) { this.header = value; }

    @JsonProperty("Body")
    public Body getBody() { return body; }
    @JsonProperty("Body")
    public void setBody(Body value) { this.body = value; }
}
