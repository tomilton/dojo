package co.com.nequi.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetailReq {

    private String phoneNumber;
    private String document;
    private String bankId;

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("document")
    public String getDocument() {
        return document;
    }
    @JsonProperty("document")
    public void setDocument(String document) {
        this.document = document;
    }

    @JsonProperty("bankId")
    public String getBankId() {
        return bankId;
    }
    @JsonProperty("bankId")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
}
