package co.com.nequi.nequiservice.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreezeAccountRs {
    @JsonProperty("FreezeAccountRs_Customdata")
    private FreezeAccountRsCustomData freezeAccountRsCustomData;
}
