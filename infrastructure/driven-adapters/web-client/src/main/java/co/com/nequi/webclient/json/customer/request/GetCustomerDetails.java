package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCustomerDetails {

    @JsonProperty("mobileNo")
    private String phoneNumber;
    @JsonProperty("documentId")
    private String document;
    private String bankId;

}
