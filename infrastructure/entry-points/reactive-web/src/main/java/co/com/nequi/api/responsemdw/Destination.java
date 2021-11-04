package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

public class Destination {
    private String name;
    private String namespace;
    private String operation;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("namespace")
    public String getNamespace() { return namespace; }
    @JsonProperty("namespace")
    public void setNamespace(String value) { this.namespace = value; }

    @JsonProperty("operation")
    public String getOperation() { return operation; }
    @JsonProperty("operation")
    public void setOperation(String value) { this.operation = value; }
}
