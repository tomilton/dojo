package co.com.nequi.model.responsemdw;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseMdw {
    private ResponseHeaderOut responseHeaderOut;
    private String omitXMLDeclaration;
}
