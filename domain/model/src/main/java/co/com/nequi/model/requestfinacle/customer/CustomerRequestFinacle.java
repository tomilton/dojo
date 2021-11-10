package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder(toBuilder = true)
public class CustomerRequestFinacle {
    private List<RelationshipmanagerInfo> relationshipmanagerInfo;
    private List<EmailDetail> emailDetails;
    private List<PhoneDetail> phoneDetails;
    private List<Address> addresses;
    private List<AlternateLanguage> alternateLanguages;
    private List<IdentificationDocument> identificationDocuments;
    private List<EmploymentDetail> employmentDetails;
    private List<RetailIncome> retailIncome;
    private List<RetailExpense> retailExpense;
    private List<BankStaff> bankStaffs;
    private List<CurrencyDetail> currencyDetails;
    private String cifID;
    private String subEntityType;
    private String custType;
    private String dob;
    private String shortName;
    private String minor;
    private String seniorCitizen;
    private String primaryBranch;
    private String taxIDType;
    private String taxIdentificationNumber;
    private String taxExemptionCode;
    private String taxResident;
    private String firstname;
    private String middlename;
    private String lastname;
    private String motherMaidenName;
    private String gender;
    private String relStartDate;
    private String retailSegment;
    private String maritalStatus;
    private String countryofPrimaryCitizenship;
    private String cifStatus;
}
