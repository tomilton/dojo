package co.com.nequi.model.responsefinacle.customer;

import java.util.List;

@lombok.Data
public class Meta {
    private String requestuuid;
    private String globaluuid;
    private String contexturl;
    private List<ErrorDetail> errorDetails;
}
