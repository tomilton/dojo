package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class BankStaff {
    private String empDtlsApplicable;
    private String staffIndicator;
    private String empID;
    private String relationship;
    private String rowStatus;
}
