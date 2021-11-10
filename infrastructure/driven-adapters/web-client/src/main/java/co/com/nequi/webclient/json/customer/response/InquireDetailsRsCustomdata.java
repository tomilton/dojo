package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InquireDetailsRsCustomdata {

    private OutputData outputData;

    @JsonProperty("outputData")
    public OutputData getOutputData() { return outputData; }
    @JsonProperty("outputData")
    public void setOutputData(OutputData value) { this.outputData = value; }
}
