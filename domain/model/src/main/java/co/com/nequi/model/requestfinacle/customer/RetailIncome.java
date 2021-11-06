package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class RetailIncome {
    private String incomeType;
    private String incomeFrequency;
    private String currency;
    private String incomeAmount;
    private String rowStatus;
}
