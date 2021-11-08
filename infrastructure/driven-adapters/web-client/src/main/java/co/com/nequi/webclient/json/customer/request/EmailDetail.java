package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class EmailDetail {
    private String emailID;
    private String emailType;
    private String preferred;
    private String rowStatus;

    @JsonProperty("emailId")
    public String getEmailID() { return emailID; }
    @JsonProperty("emailId")
    public void setEmailID(String value) { this.emailID = value; }

    @JsonProperty("emailType")
    public String getEmailType() { return emailType; }
    @JsonProperty("emailType")
    public void setEmailType(String value) { this.emailType = value; }

    @JsonProperty("preferred")
    public String getPreferred() { return preferred; }
    @JsonProperty("preferred")
    public void setPreferred(String value) { this.preferred = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
