package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class MessageContext {
    private Property property;

    @JsonProperty("property")
    public Property getProperty() { return property; }
    @JsonProperty("property")
    public void setProperty(Property value) { this.property = value; }
}
