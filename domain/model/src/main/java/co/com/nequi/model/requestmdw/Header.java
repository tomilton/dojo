package co.com.nequi.model.requestmdw;

import java.time.OffsetDateTime;

public class Header {
    private String systemID;
    private String messageID;
    private String invokerDateTime;
    private SecurityCredential securityCredential;
    private Destination destination;
    private MessageContext messageContext;

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String value) {
        this.systemID = value;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String value) {
        this.messageID = value;
    }

    public String getInvokerDateTime() {
        return invokerDateTime;
    }

    public void setInvokerDateTime(String value) {
        this.invokerDateTime = value;
    }

    public SecurityCredential getSecurityCredential() {
        return securityCredential;
    }

    public void setSecurityCredential(SecurityCredential value) {
        this.securityCredential = value;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination value) {
        this.destination = value;
    }

    public MessageContext getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(MessageContext value) {
        this.messageContext = value;
    }
}
