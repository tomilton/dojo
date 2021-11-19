package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Header {
    private String systemID;
    private String messageID;
    private String invokerDateTime;
    private SecurityCredential securityCredential;
    private Destination destination;
    private MessageContext messageContext;

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

    @JsonProperty("securityCredential")
    public SecurityCredential getSecurityCredential() {
        return securityCredential;
    }

    @JsonProperty("securityCredential")
    public void setSecurityCredential(SecurityCredential value) {
        this.securityCredential = value;
    }

    @JsonProperty("destination")
    public Destination getDestination() {
        return destination;
    }

    @JsonProperty("destination")
    public void setDestination(Destination value) {
        this.destination = value;
    }

    @JsonProperty("messageContext")
    public MessageContext getMessageContext() {
        return messageContext;
    }

    @JsonProperty("messageContext")
    public void setMessageContext(MessageContext value) {
        this.messageContext = value;
    }
}
