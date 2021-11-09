package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.*;

public class ErrorDetail {
    private String errorcode;
    private String errordesc;

    @JsonProperty("errorcode")
    public String getErrorcode() { return errorcode; }
    @JsonProperty("errorcode")
    public void setErrorcode(String value) { this.errorcode = value; }

    @JsonProperty("errordesc")
    public String getErrordesc() { return errordesc; }
    @JsonProperty("errordesc")
    public void setErrordesc(String value) { this.errordesc = value; }
}
