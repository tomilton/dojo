package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseHeaderOut {
    private Header header;
    private Body body;

}
