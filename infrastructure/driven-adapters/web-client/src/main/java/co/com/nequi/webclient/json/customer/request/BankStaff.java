package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class BankStaff {
    private String empDtlsApplicable;
    private String staffIndicator;
    private String empID;
    private String relationship;
    private String rowStatus;

    @JsonProperty("empDtlsApplicable")
    public String getEmpDtlsApplicable() { return empDtlsApplicable; }
    @JsonProperty("empDtlsApplicable")
    public void setEmpDtlsApplicable(String value) { this.empDtlsApplicable = value; }

    @JsonProperty("staffIndicator")
    public String getStaffIndicator() { return staffIndicator; }
    @JsonProperty("staffIndicator")
    public void setStaffIndicator(String value) { this.staffIndicator = value; }

    @JsonProperty("empID")
    public String getEmpID() { return empID; }
    @JsonProperty("empID")
    public void setEmpID(String value) { this.empID = value; }

    @JsonProperty("relationship")
    public String getRelationship() { return relationship; }
    @JsonProperty("relationship")
    public void setRelationship(String value) { this.relationship = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
