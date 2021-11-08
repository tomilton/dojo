package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.*;

public class Data {
    private String cifID;

    @JsonProperty("cifId")
    public String getCifID() {
        return cifID;
    }

    @JsonProperty("cifId")
    public void setCifID(String value) {
        this.cifID = value;
    }
}
