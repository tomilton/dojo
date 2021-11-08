package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class RetailExpense {
    private String expenseType;
    private String expenseFrequency;
    private String currency;
    private String expenseAmount;
    private String rowStatus;
}
