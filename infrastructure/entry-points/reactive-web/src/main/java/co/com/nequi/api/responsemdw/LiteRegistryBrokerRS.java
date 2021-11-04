package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

public class LiteRegistryBrokerRS {
    private String cifID;

    @JsonProperty("cifId")
    public String getCifID() { return cifID; }
    @JsonProperty("cifId")
    public void setCifID(String value) { this.cifID = value; }
}
