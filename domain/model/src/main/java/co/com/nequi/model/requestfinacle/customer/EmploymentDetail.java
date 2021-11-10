package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
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
    private String employmentStartDate;
    private String employmentEndDate;
    private String rowStatus;
}
