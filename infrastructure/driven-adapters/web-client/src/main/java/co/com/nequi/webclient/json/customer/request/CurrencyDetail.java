package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class CurrencyDetail {
    private String ccy;
    private String prefentialExpiryDate;
    private String creditDiscountPercentage;
    private String debitDiscountPercentage;
    private String withholdingTaxPercentage;
    private String withholdingTaxFloorLimit;
    private String rowStatus;

    @JsonProperty("ccy")
    public String getCcy() { return ccy; }
    @JsonProperty("ccy")
    public void setCcy(String value) { this.ccy = value; }

    @JsonProperty("prefentialExpiryDate")
    public String getPrefentialExpiryDate() { return prefentialExpiryDate; }
    @JsonProperty("prefentialExpiryDate")
    public void setPrefentialExpiryDate(String value) { this.prefentialExpiryDate = value; }

    @JsonProperty("creditDiscountPercentage")
    public String getCreditDiscountPercentage() { return creditDiscountPercentage; }
    @JsonProperty("creditDiscountPercentage")
    public void setCreditDiscountPercentage(String value) { this.creditDiscountPercentage = value; }

    @JsonProperty("debitDiscountPercentage")
    public String getDebitDiscountPercentage() { return debitDiscountPercentage; }
    @JsonProperty("debitDiscountPercentage")
    public void setDebitDiscountPercentage(String value) { this.debitDiscountPercentage = value; }

    @JsonProperty("withholdingTaxPercentage")
    public String getWithholdingTaxPercentage() { return withholdingTaxPercentage; }
    @JsonProperty("withholdingTaxPercentage")
    public void setWithholdingTaxPercentage(String value) { this.withholdingTaxPercentage = value; }

    @JsonProperty("withholdingTaxFloorLimit")
    public String getWithholdingTaxFloorLimit() { return withholdingTaxFloorLimit; }
    @JsonProperty("withholdingTaxFloorLimit")
    public void setWithholdingTaxFloorLimit(String value) { this.withholdingTaxFloorLimit = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
