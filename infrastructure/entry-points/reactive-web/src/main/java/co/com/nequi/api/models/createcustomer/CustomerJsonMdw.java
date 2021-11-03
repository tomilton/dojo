package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class CustomerJsonMdw {
    private LiteRegistryBrokerRQ liteRegistryBrokerRQ;

    @JsonProperty("liteRegistryBrokerRQ")
    public LiteRegistryBrokerRQ getLiteRegistryBrokerRQ() { return liteRegistryBrokerRQ; }
    @JsonProperty("liteRegistryBrokerRQ")
    public void setLiteRegistryBrokerRQ(LiteRegistryBrokerRQ value) { this.liteRegistryBrokerRQ = value; }
}
