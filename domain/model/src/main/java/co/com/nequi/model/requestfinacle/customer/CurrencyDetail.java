package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class CurrencyDetail {
    private String ccy;
    private String prefentialExpiryDate;
    private String creditDiscountPercentage;
    private String debitDiscountPercentage;
    private String withholdingTaxPercentage;
    private String withholdingTaxFloorLimit;
    private String rowStatus;
}
