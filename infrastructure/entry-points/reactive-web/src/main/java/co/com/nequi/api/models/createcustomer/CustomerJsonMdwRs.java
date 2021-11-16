package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class CustomerJsonMdwRs {
    private LiteRegistryBrokerRS liteRegistryBrokerRS;

    @JsonProperty("liteRegistryBrokerRS")
    public LiteRegistryBrokerRS getLiteRegistryBrokerRS() { return liteRegistryBrokerRS; }
    @JsonProperty("liteRegistryBrokerRS")
    public void setLiteRegistryBrokerRS(LiteRegistryBrokerRS value) { this.liteRegistryBrokerRS = value; }
}
