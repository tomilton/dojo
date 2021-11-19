package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDetailRequestJSON {

    @JsonProperty("InquireDetailsRq")
    private GetCustomerDetails getCustomerDetails;

}
