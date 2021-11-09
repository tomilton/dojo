package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;
import java.util.List;

public class CustomerRequestJSON {
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
    private OffsetDateTime dob;
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
    private OffsetDateTime relStartDate;
    private String retailSegment;
    private String maritalStatus;
    private String countryofPrimaryCitizenship;
    private String cifStatus;

    @JsonProperty("relationshipmanagerInfo")
    public List<RelationshipmanagerInfo> getRelationshipmanagerInfo() { return relationshipmanagerInfo; }
    @JsonProperty("relationshipmanagerInfo")
    public void setRelationshipmanagerInfo(List<RelationshipmanagerInfo> value) { this.relationshipmanagerInfo = value; }

    @JsonProperty("emailDetails")
    public List<EmailDetail> getEmailDetails() { return emailDetails; }
    @JsonProperty("emailDetails")
    public void setEmailDetails(List<EmailDetail> value) { this.emailDetails = value; }

    @JsonProperty("phoneDetails")
    public List<PhoneDetail> getPhoneDetails() { return phoneDetails; }
    @JsonProperty("phoneDetails")
    public void setPhoneDetails(List<PhoneDetail> value) { this.phoneDetails = value; }

    @JsonProperty("addresses")
    public List<Address> getAddresses() { return addresses; }
    @JsonProperty("addresses")
    public void setAddresses(List<Address> value) { this.addresses = value; }

    @JsonProperty("alternateLanguages")
    public List<AlternateLanguage> getAlternateLanguages() { return alternateLanguages; }
    @JsonProperty("alternateLanguages")
    public void setAlternateLanguages(List<AlternateLanguage> value) { this.alternateLanguages = value; }

    @JsonProperty("identificationDocuments")
    public List<IdentificationDocument> getIdentificationDocuments() { return identificationDocuments; }
    @JsonProperty("identificationDocuments")
    public void setIdentificationDocuments(List<IdentificationDocument> value) { this.identificationDocuments = value; }

    @JsonProperty("employmentDetails")
    public List<EmploymentDetail> getEmploymentDetails() { return employmentDetails; }
    @JsonProperty("employmentDetails")
    public void setEmploymentDetails(List<EmploymentDetail> value) { this.employmentDetails = value; }

    @JsonProperty("retailIncome")
    public List<RetailIncome> getRetailIncome() { return retailIncome; }
    @JsonProperty("retailIncome")
    public void setRetailIncome(List<RetailIncome> value) { this.retailIncome = value; }

    @JsonProperty("retailExpense")
    public List<RetailExpense> getRetailExpense() { return retailExpense; }
    @JsonProperty("retailExpense")
    public void setRetailExpense(List<RetailExpense> value) { this.retailExpense = value; }

    @JsonProperty("bankStaffs")
    public List<BankStaff> getBankStaffs() { return bankStaffs; }
    @JsonProperty("bankStaffs")
    public void setBankStaffs(List<BankStaff> value) { this.bankStaffs = value; }

    @JsonProperty("currencyDetails")
    public List<CurrencyDetail> getCurrencyDetails() { return currencyDetails; }
    @JsonProperty("currencyDetails")
    public void setCurrencyDetails(List<CurrencyDetail> value) { this.currencyDetails = value; }

    @JsonProperty("cifId")
    public String getCifID() { return cifID; }
    @JsonProperty("cifId")
    public void setCifID(String value) { this.cifID = value; }

    @JsonProperty("subEntityType")
    public String getSubEntityType() { return subEntityType; }
    @JsonProperty("subEntityType")
    public void setSubEntityType(String value) { this.subEntityType = value; }

    @JsonProperty("custType")
    public String getCustType() { return custType; }
    @JsonProperty("custType")
    public void setCustType(String value) { this.custType = value; }

    @JsonProperty("dob")
    public OffsetDateTime getDob() { return dob; }
    @JsonProperty("dob")
    public void setDob(OffsetDateTime value) { this.dob = value; }

    @JsonProperty("shortName")
    public String getShortName() { return shortName; }
    @JsonProperty("shortName")
    public void setShortName(String value) { this.shortName = value; }

    @JsonProperty("minor")
    public String getMinor() { return minor; }
    @JsonProperty("minor")
    public void setMinor(String value) { this.minor = value; }

    @JsonProperty("seniorCitizen")
    public String getSeniorCitizen() { return seniorCitizen; }
    @JsonProperty("seniorCitizen")
    public void setSeniorCitizen(String value) { this.seniorCitizen = value; }

    @JsonProperty("primaryBranch")
    public String getPrimaryBranch() { return primaryBranch; }
    @JsonProperty("primaryBranch")
    public void setPrimaryBranch(String value) { this.primaryBranch = value; }

    @JsonProperty("taxIdType")
    public String getTaxIDType() { return taxIDType; }
    @JsonProperty("taxIdType")
    public void setTaxIDType(String value) { this.taxIDType = value; }

    @JsonProperty("taxIdentificationNumber")
    public String getTaxIdentificationNumber() { return taxIdentificationNumber; }
    @JsonProperty("taxIdentificationNumber")
    public void setTaxIdentificationNumber(String value) { this.taxIdentificationNumber = value; }

    @JsonProperty("taxExemptionCode")
    public String getTaxExemptionCode() { return taxExemptionCode; }
    @JsonProperty("taxExemptionCode")
    public void setTaxExemptionCode(String value) { this.taxExemptionCode = value; }

    @JsonProperty("taxResident")
    public String getTaxResident() { return taxResident; }
    @JsonProperty("taxResident")
    public void setTaxResident(String value) { this.taxResident = value; }

    @JsonProperty("firstname")
    public String getFirstname() { return firstname; }
    @JsonProperty("firstname")
    public void setFirstname(String value) { this.firstname = value; }

    @JsonProperty("middlename")
    public String getMiddlename() { return middlename; }
    @JsonProperty("middlename")
    public void setMiddlename(String value) { this.middlename = value; }

    @JsonProperty("lastname")
    public String getLastname() { return lastname; }
    @JsonProperty("lastname")
    public void setLastname(String value) { this.lastname = value; }

    @JsonProperty("motherMaidenName")
    public String getMotherMaidenName() { return motherMaidenName; }
    @JsonProperty("motherMaidenName")
    public void setMotherMaidenName(String value) { this.motherMaidenName = value; }

    @JsonProperty("gender")
    public String getGender() { return gender; }
    @JsonProperty("gender")
    public void setGender(String value) { this.gender = value; }

    @JsonProperty("relStartDate")
    public OffsetDateTime getRelStartDate() { return relStartDate; }
    @JsonProperty("relStartDate")
    public void setRelStartDate(OffsetDateTime value) { this.relStartDate = value; }

    @JsonProperty("retailSegment")
    public String getRetailSegment() { return retailSegment; }
    @JsonProperty("retailSegment")
    public void setRetailSegment(String value) { this.retailSegment = value; }

    @JsonProperty("maritalStatus")
    public String getMaritalStatus() { return maritalStatus; }
    @JsonProperty("maritalStatus")
    public void setMaritalStatus(String value) { this.maritalStatus = value; }

    @JsonProperty("countryofPrimaryCitizenship")
    public String getCountryofPrimaryCitizenship() { return countryofPrimaryCitizenship; }
    @JsonProperty("countryofPrimaryCitizenship")
    public void setCountryofPrimaryCitizenship(String value) { this.countryofPrimaryCitizenship = value; }

    @JsonProperty("cifStatus")
    public String getCifStatus() { return cifStatus; }
    @JsonProperty("cifStatus")
    public void setCifStatus(String value) { this.cifStatus = value; }
}
