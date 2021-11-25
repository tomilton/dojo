package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Getter
@Setter
public class CustomerResponseJSON2 {
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private DataLock data;
}
