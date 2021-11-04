package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

public class ResponseHeaderOut {
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
