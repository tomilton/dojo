package co.com.nequi.nequiservice.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class FreezeAccountRsCustomDataMock {
    @JsonProperty("FreezeAccountRs_Customdata")
    private FreezeAccountRsCustomData freezeAccountRs_Customdata;
}
