package co.com.nequi.api.responsemdw;

import com.fasterxml.jackson.annotation.*;

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
