package co.com.nequi.nequiservice.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FreezeAccountRsCustomDataDetail {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Message")
    private String message;

}
