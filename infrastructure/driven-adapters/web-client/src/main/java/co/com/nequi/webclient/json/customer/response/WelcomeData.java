package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WelcomeData {

    private Meta meta;
    private DataData data;

    @JsonProperty("meta")
    public Meta getMeta() { return meta; }
    @JsonProperty("meta")
    public void setMeta(Meta value) { this.meta = value; }

    @JsonProperty("data")
    public DataData getData() { return data; }
    @JsonProperty("data")
    public void setData(DataData value) { this.data = value; }
}
