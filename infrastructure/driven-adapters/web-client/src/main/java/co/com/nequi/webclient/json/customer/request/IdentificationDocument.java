package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class IdentificationDocument {
    private String documentType;
    private String documentNumber;
    private String issuingAuthority;
    private String countryOfIssue;
    private String placeOfIssue;
    private OffsetDateTime issueDate;
    private OffsetDateTime expiryDate;
    private String identificationType;
    private String id;
    private String rowStatus;

    @JsonProperty("documentType")
    public String getDocumentType() { return documentType; }
    @JsonProperty("documentType")
    public void setDocumentType(String value) { this.documentType = value; }

    @JsonProperty("documentNumber")
    public String getDocumentNumber() { return documentNumber; }
    @JsonProperty("documentNumber")
    public void setDocumentNumber(String value) { this.documentNumber = value; }

    @JsonProperty("issuingAuthority")
    public String getIssuingAuthority() { return issuingAuthority; }
    @JsonProperty("issuingAuthority")
    public void setIssuingAuthority(String value) { this.issuingAuthority = value; }

    @JsonProperty("countryOfIssue")
    public String getCountryOfIssue() { return countryOfIssue; }
    @JsonProperty("countryOfIssue")
    public void setCountryOfIssue(String value) { this.countryOfIssue = value; }

    @JsonProperty("placeOfIssue")
    public String getPlaceOfIssue() { return placeOfIssue; }
    @JsonProperty("placeOfIssue")
    public void setPlaceOfIssue(String value) { this.placeOfIssue = value; }

    @JsonProperty("issueDate")
    public OffsetDateTime getIssueDate() { return issueDate; }
    @JsonProperty("issueDate")
    public void setIssueDate(OffsetDateTime value) { this.issueDate = value; }

    @JsonProperty("expiryDate")
    public OffsetDateTime getExpiryDate() { return expiryDate; }
    @JsonProperty("expiryDate")
    public void setExpiryDate(OffsetDateTime value) { this.expiryDate = value; }

    @JsonProperty("identificationType")
    public String getIdentificationType() { return identificationType; }
    @JsonProperty("identificationType")
    public void setIdentificationType(String value) { this.identificationType = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
