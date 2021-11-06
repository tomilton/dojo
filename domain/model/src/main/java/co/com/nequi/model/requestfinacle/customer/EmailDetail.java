package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class EmailDetail {
    private String emailID;
    private String emailType;
    private String preferred;
    private String rowStatus;
}
