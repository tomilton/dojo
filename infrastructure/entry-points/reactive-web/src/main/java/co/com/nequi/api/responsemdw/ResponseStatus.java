package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

public class ResponseStatus {
    private String statusCode;
    private String statusDesc;
    private String errorMessage;
    private String system;

    @JsonProperty("statusCode")
    public String getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    @JsonProperty("statusDesc")
    public String getStatusDesc() {
        return statusDesc;
    }

    @JsonProperty("statusDesc")
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    @JsonProperty("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    @JsonProperty("system")
    public String getSystem() {
        return system;
    }

    @JsonProperty("system")
    public void setSystem(String value) {
        this.system = value;
    }
}
