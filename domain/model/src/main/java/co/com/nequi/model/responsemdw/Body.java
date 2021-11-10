package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder(toBuilder = true)
@ToString
public class Body {
    private Object any;
}
