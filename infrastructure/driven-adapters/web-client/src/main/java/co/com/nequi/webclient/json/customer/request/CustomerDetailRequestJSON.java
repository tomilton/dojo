package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetailRequestJSON {

    private GetCustomerDetails getCustomerDetails;

    @JsonProperty("InquireDetailsRq")
    public GetCustomerDetails getGetCustomerDetails() {
        return getCustomerDetails;
    }
    @JsonProperty("InquireDetailsRq")
    public void setGetCustomerDetails(GetCustomerDetails getCustomerDetails) {
        this.getCustomerDetails = getCustomerDetails;
    }
}
