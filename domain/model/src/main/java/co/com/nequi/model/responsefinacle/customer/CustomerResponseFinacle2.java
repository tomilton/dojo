package co.com.nequi.model.responsefinacle.customer;

import co.com.nequi.model.responsefinacle.detailprueba.DataLock;
import co.com.nequi.model.responsefinacle.detailprueba.Meta;
import lombok.*;


@Getter
@Setter
public class CustomerResponseFinacle2 {
    private Meta meta;
    private DataLock data;
    private ErrorDetail errorDetail;
}
