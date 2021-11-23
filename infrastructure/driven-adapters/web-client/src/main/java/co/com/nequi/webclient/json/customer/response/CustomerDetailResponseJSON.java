package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailResponseJSON {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private WelcomeData data;

}