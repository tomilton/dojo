package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;

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
