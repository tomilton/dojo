package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class IdentificationDocument {
    private String documentType;
    private String documentNumber;
    private String issuingAuthority;
    private String countryOfIssue;
    private String placeOfIssue;
    private String issueDate;
    private String expiryDate;
    private String identificationType;
    private String id;
    private String rowStatus;
}
