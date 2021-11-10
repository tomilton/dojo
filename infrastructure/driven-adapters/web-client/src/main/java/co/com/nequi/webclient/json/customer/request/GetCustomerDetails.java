package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCustomerDetails {

    private String phoneNumber;
    private String document;
    private String bankId;

    @JsonProperty("mobileNo")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @JsonProperty("mobileNo")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @JsonProperty("documentId")
    public String getDocument() {
        return document;
    }
    @JsonProperty("documentId")
    public void setDocument(String document) {
        this.document = document;
    }
}
