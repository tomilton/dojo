package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataData {

    private InquireDetailsRsCustomdata inquireDetailsRsCustomdata;

    @JsonProperty("InquireDetailsRs_Customdata")
    public InquireDetailsRsCustomdata getInquireDetailsRsCustomdata() { return inquireDetailsRsCustomdata; }
    @JsonProperty("InquireDetailsRs_Customdata")
    public void setInquireDetailsRsCustomdata(InquireDetailsRsCustomdata value) { this.inquireDetailsRsCustomdata = value; }

}
