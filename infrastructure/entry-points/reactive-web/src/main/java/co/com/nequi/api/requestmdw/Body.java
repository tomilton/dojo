package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Body {
    private Object any;

    @JsonProperty("any")
    public Object getAny() {
        return any;
    }

    @JsonProperty("any")
    public void setAny(Object value) {
        this.any = value;
    }
}
