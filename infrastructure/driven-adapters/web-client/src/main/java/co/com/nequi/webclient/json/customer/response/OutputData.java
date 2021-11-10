package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputData {

    private OutputDataData data;

    @JsonProperty("data")
    public OutputDataData getData() { return data; }
    @JsonProperty("data")
    public void setData(OutputDataData value) { this.data = value; }
}
