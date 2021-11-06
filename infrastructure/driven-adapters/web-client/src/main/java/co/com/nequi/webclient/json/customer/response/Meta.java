package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class Meta {
    private String requestuuid;
    private String globaluuid;
    private String contexturl;
    private List<ErrorDetail> errorDetails;

    @JsonProperty("REQUESTUUID")
    public String getRequestuuid() { return requestuuid; }
    @JsonProperty("REQUESTUUID")
    public void setRequestuuid(String value) { this.requestuuid = value; }

    @JsonProperty("GLOBALUUID")
    public String getGlobaluuid() { return globaluuid; }
    @JsonProperty("GLOBALUUID")
    public void setGlobaluuid(String value) { this.globaluuid = value; }

    @JsonProperty("contexturl")
    public String getContexturl() { return contexturl; }
    @JsonProperty("contexturl")
    public void setContexturl(String value) { this.contexturl = value; }

    @JsonProperty("errorDetails")
    public List<ErrorDetail> getErrorDetails() { return errorDetails; }
    @JsonProperty("errorDetails")
    public void setErrorDetails(List<ErrorDetail> value) { this.errorDetails = value; }
}
