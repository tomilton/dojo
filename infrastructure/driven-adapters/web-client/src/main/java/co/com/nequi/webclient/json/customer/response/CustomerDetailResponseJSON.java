package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetailResponseJSON {

    private Meta meta;
    private WelcomeData data;

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }
    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    @JsonProperty("data")
    public WelcomeData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(WelcomeData data) {
        this.data = data;
    }
}
