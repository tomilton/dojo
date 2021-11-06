package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class RetailExpense {
    private String expenseType;
    private String expenseFrequency;
    private String currency;
    private String expenseAmount;
    private String rowStatus;

    @JsonProperty("expenseType")
    public String getExpenseType() { return expenseType; }
    @JsonProperty("expenseType")
    public void setExpenseType(String value) { this.expenseType = value; }

    @JsonProperty("expenseFrequency")
    public String getExpenseFrequency() { return expenseFrequency; }
    @JsonProperty("expenseFrequency")
    public void setExpenseFrequency(String value) { this.expenseFrequency = value; }

    @JsonProperty("currency")
    public String getCurrency() { return currency; }
    @JsonProperty("currency")
    public void setCurrency(String value) { this.currency = value; }

    @JsonProperty("expenseAmount")
    public String getExpenseAmount() { return expenseAmount; }
    @JsonProperty("expenseAmount")
    public void setExpenseAmount(String value) { this.expenseAmount = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
