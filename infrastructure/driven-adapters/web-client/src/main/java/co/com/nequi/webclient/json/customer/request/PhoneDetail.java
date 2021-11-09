package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class PhoneDetail {
    private String countryCode;
    private String phoneType;
    private String phoneNumber;
    private String preferred;
    private String rowStatus;

    @JsonProperty("countryCode")
    public String getCountryCode() { return countryCode; }
    @JsonProperty("countryCode")
    public void setCountryCode(String value) { this.countryCode = value; }

    @JsonProperty("phoneType")
    public String getPhoneType() { return phoneType; }
    @JsonProperty("phoneType")
    public void setPhoneType(String value) { this.phoneType = value; }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    @JsonProperty("preferred")
    public String getPreferred() { return preferred; }
    @JsonProperty("preferred")
    public void setPreferred(String value) { this.preferred = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
