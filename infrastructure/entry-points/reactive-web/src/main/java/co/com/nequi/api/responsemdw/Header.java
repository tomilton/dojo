package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

import java.time.OffsetDateTime;

public class Header {
    private String systemID;
    private String messageID;
    private String invokerDateTime;
    private Destination destination;
    private ResponseStatus responseStatus;

    @JsonProperty("systemId")
    public String getSystemID() {
        return systemID;
    }

    @JsonProperty("systemId")
    public void setSystemID(String value) {
        this.systemID = value;
    }

    @JsonProperty("messageId")
    public String getMessageID() {
        return messageID;
    }

    @JsonProperty("messageId")
    public void setMessageID(String value) {
        this.messageID = value;
    }

    @JsonProperty("invokerDateTime")
    public String getInvokerDateTime() {
        return invokerDateTime;
    }

    @JsonProperty("invokerDateTime")
    public void setInvokerDateTime(String value) {
        this.invokerDateTime = value;
    }

    @JsonProperty("destination")
    public Destination getDestination() {
        return destination;
    }

    @JsonProperty("destination")
    public void setDestination(Destination value) {
        this.destination = value;
    }

    @JsonProperty("responseStatus")
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    @JsonProperty("responseStatus")
    public void setResponseStatus(ResponseStatus value) {
        this.responseStatus = value;
    }
}
