package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.*;

public class CustomerResponseJSON {
    private Meta meta;
    private Data data;

    @JsonProperty("meta")
    public Meta getMeta() { return meta; }
    @JsonProperty("meta")
    public void setMeta(Meta value) { this.meta = value; }

    @JsonProperty("data")
    public Data getData() { return data; }
    @JsonProperty("data")
    public void setData(Data value) { this.data = value; }
}
