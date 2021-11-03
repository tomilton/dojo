package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseStatus {
    private String statusCode;
    private String statusDesc;
    private String errorMessage;
    private String system;

}
