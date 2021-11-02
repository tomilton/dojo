package co.com.nequi.model.responsemdw;

import java.time.OffsetDateTime;

public class Header {
    private String systemID;
    private String messageID;
    private String invokerDateTime;
    private Destination destination;
    private ResponseStatus responseStatus;

    public String getSystemID() { return systemID; }
    public void setSystemID(String value) { this.systemID = value; }

    public String getMessageID() { return messageID; }
    public void setMessageID(String value) { this.messageID = value; }

    public String getInvokerDateTime() {
        return invokerDateTime;
    }

    public void setInvokerDateTime(String value) { this.invokerDateTime = value; }

    public Destination getDestination() { return destination; }
    public void setDestination(Destination value) { this.destination = value; }

    public ResponseStatus getResponseStatus() { return responseStatus; }
    public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
}
