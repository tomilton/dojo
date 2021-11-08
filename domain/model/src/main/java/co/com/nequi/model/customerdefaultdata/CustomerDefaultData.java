package co.com.nequi.model.customerdefaultdata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CustomerDefaultData {

    private String CustStatus;
    private String PrimaryRMID;
    private String TFPartyInd;

}
