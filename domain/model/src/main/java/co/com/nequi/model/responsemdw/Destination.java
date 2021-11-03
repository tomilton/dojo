package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Destination {
    private String name;
    private String namespace;
    private String operation;

}
