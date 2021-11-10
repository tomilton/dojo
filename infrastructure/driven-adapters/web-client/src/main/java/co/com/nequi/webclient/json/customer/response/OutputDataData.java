package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OutputDataData {

    private String status;
    private String cifID;
    private String fullName;
    private String preferredname;
    private String documentType;
    private String documentID;
    private String mobileNo;
    private String emailID;
    private String foracid;
    private Date acctOpnDate;
    private String acctStatus;

    @JsonProperty("Status")
    public String getStatus() { return status; }
    @JsonProperty("Status")
    public void setStatus(String value) { this.status = value; }

    @JsonProperty("CIF_ID")
    public String getCifID() { return cifID; }
    @JsonProperty("CIF_ID")
    public void setCifID(String value) { this.cifID = value; }

    @JsonProperty("FULL_NAME")
    public String getFullName() { return fullName; }
    @JsonProperty("FULL_NAME")
    public void setFullName(String value) { this.fullName = value; }

    @JsonProperty("PREFERREDNAME")
    public String getPreferredname() { return preferredname; }
    @JsonProperty("PREFERREDNAME")
    public void setPreferredname(String value) { this.preferredname = value; }

    @JsonProperty("DOCUMENT_TYPE")
    public String getDocumentType() { return documentType; }
    @JsonProperty("DOCUMENT_TYPE")
    public void setDocumentType(String value) { this.documentType = value; }

    @JsonProperty("DOCUMENT_ID")
    public String getDocumentID() { return documentID; }
    @JsonProperty("DOCUMENT_ID")
    public void setDocumentID(String value) { this.documentID = value; }

    @JsonProperty("MOBILE_NO")
    public String getMobileNo() { return mobileNo; }
    @JsonProperty("MOBILE_NO")
    public void setMobileNo(String value) { this.mobileNo = value; }

    @JsonProperty("EMAIL_ID")
    public String getEmailID() { return emailID; }
    @JsonProperty("EMAIL_ID")
    public void setEmailID(String value) { this.emailID = value; }

    @JsonProperty("FORACID")
    public String getForacid() { return foracid; }
    @JsonProperty("FORACID")
    public void setForacid(String value) { this.foracid = value; }

    @JsonProperty("ACCT_OPN_DATE")
    public Date getAcctOpnDate() { return acctOpnDate; }
    @JsonProperty("ACCT_OPN_DATE")
    public void setAcctOpnDate(Date value) { this.acctOpnDate = value; }

    @JsonProperty("ACCT_STATUS")
    public String getAcctStatus() { return acctStatus; }
    @JsonProperty("ACCT_STATUS")
    public void setAcctStatus(String value) { this.acctStatus = value; }
}
