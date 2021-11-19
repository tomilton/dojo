package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Output {

    @JsonProperty("Status")
    private String status;
    @JsonProperty("CIF_ID")
    private String cifID;
    @JsonProperty("FULL_NAME")
    private String fullName;
    @JsonProperty("PREFERREDNAME")
    private String preferredname;
    @JsonProperty("DOCUMENT_TYPE")
    private String documentType;
    @JsonProperty("DOCUMENT_ID")
    private String documentID;
    @JsonProperty("MOBILE_NO")
    private String mobileNo;
    @JsonProperty("EMAIL_ID")
    private String emailID;
    @JsonProperty("FORACID")
    private String foracid;
    @JsonProperty("ACCT_OPN_DATE")
    private Date acctOpnDate;
    @JsonProperty("ACCT_STATUS")
    private String acctStatus;
}
