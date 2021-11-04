package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Header {
    private String systemID;
    private String messageID;
    private String invokerDateTime;
    private Destination destination;
    private ResponseStatus responseStatus;
}
