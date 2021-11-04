package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class DemographicInfo {
    private String grossIncome;
    private String employmentType;
    private String empType;

    @JsonProperty("grossIncome")
    public String getGrossIncome() { return grossIncome; }
    @JsonProperty("grossIncome")
    public void setGrossIncome(String value) { this.grossIncome = value; }

    @JsonProperty("employmentType")
    public String getEmploymentType() { return employmentType; }
    @JsonProperty("employmentType")
    public void setEmploymentType(String value) { this.employmentType = value; }

    @JsonProperty("empType")
    public String getEmpType() { return empType; }
    @JsonProperty("empType")
    public void setEmpType(String value) { this.empType = value; }
}
