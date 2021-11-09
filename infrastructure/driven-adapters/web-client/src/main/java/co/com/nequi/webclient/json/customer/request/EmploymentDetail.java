package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class EmploymentDetail {
    private String id;
    private String natureOfEmployment;
    private String employmentStatus;
    private String employer;
    private String employerType;
    private String industry;
    private String city;
    private String state;
    private String countryOfDeputation;
    private String occupation;
    private String designation;
    private OffsetDateTime employmentStartDate;
    private OffsetDateTime employmentEndDate;
    private String rowStatus;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("natureOfEmployment")
    public String getNatureOfEmployment() { return natureOfEmployment; }
    @JsonProperty("natureOfEmployment")
    public void setNatureOfEmployment(String value) { this.natureOfEmployment = value; }

    @JsonProperty("employmentStatus")
    public String getEmploymentStatus() { return employmentStatus; }
    @JsonProperty("employmentStatus")
    public void setEmploymentStatus(String value) { this.employmentStatus = value; }

    @JsonProperty("employer")
    public String getEmployer() { return employer; }
    @JsonProperty("employer")
    public void setEmployer(String value) { this.employer = value; }

    @JsonProperty("employerType")
    public String getEmployerType() { return employerType; }
    @JsonProperty("employerType")
    public void setEmployerType(String value) { this.employerType = value; }

    @JsonProperty("industry")
    public String getIndustry() { return industry; }
    @JsonProperty("industry")
    public void setIndustry(String value) { this.industry = value; }

    @JsonProperty("city")
    public String getCity() { return city; }
    @JsonProperty("city")
    public void setCity(String value) { this.city = value; }

    @JsonProperty("state")
    public String getState() { return state; }
    @JsonProperty("state")
    public void setState(String value) { this.state = value; }

    @JsonProperty("countryOfDeputation")
    public String getCountryOfDeputation() { return countryOfDeputation; }
    @JsonProperty("countryOfDeputation")
    public void setCountryOfDeputation(String value) { this.countryOfDeputation = value; }

    @JsonProperty("occupation")
    public String getOccupation() { return occupation; }
    @JsonProperty("occupation")
    public void setOccupation(String value) { this.occupation = value; }

    @JsonProperty("designation")
    public String getDesignation() { return designation; }
    @JsonProperty("designation")
    public void setDesignation(String value) { this.designation = value; }

    @JsonProperty("employmentStartDate")
    public OffsetDateTime getEmploymentStartDate() { return employmentStartDate; }
    @JsonProperty("employmentStartDate")
    public void setEmploymentStartDate(OffsetDateTime value) { this.employmentStartDate = value; }

    @JsonProperty("employmentEndDate")
    public OffsetDateTime getEmploymentEndDate() { return employmentEndDate; }
    @JsonProperty("employmentEndDate")
    public void setEmploymentEndDate(OffsetDateTime value) { this.employmentEndDate = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
