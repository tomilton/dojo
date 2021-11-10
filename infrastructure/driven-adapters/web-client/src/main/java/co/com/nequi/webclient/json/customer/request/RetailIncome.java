package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class RetailIncome {
    private String incomeType;
    private String incomeFrequency;
    private String currency;
    private String incomeAmount;
    private String rowStatus;

    @JsonProperty("incomeType")
    public String getIncomeType() { return incomeType; }
    @JsonProperty("incomeType")
    public void setIncomeType(String value) { this.incomeType = value; }

    @JsonProperty("incomeFrequency")
    public String getIncomeFrequency() { return incomeFrequency; }
    @JsonProperty("incomeFrequency")
    public void setIncomeFrequency(String value) { this.incomeFrequency = value; }

    @JsonProperty("currency")
    public String getCurrency() { return currency; }
    @JsonProperty("currency")
    public void setCurrency(String value) { this.currency = value; }

    @JsonProperty("incomeAmount")
    public String getIncomeAmount() { return incomeAmount; }
    @JsonProperty("incomeAmount")
    public void setIncomeAmount(String value) { this.incomeAmount = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
