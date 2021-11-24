package co.com.nequi.webclient.datos;


import co.com.nequi.webclient.json.customer.request.*;

import java.util.ArrayList;
import java.util.List;

public class DatosRequestJsonFinacle {

    private DatosRequestJsonFinacle() {
    }

    public static List<RelationshipmanagerInfo> buildRelationshipmanagerInfo() {
        List<RelationshipmanagerInfo> infoList = new ArrayList<>();
        RelationshipmanagerInfo relationshipmanagerInfo = new RelationshipmanagerInfo();
        relationshipmanagerInfo.setRelationshipManagerID("officia consec");
        relationshipmanagerInfo.setRelationshipManagerName("dolor mollit");
        relationshipmanagerInfo.setPrimary("in enim id");
        relationshipmanagerInfo.setDepartment("mollit Ut ipsum");
        relationshipmanagerInfo.setRowStatus("m");
        infoList.add(relationshipmanagerInfo);
        return infoList;
    }

    public static List<EmailDetail> buildEmailDetails() {
        List<EmailDetail> infoList = new ArrayList<>();
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmailID("labo");
        emailDetail.setEmailType("in officia anim D");
        emailDetail.setPreferred("sit");
        emailDetail.setRowStatus("ull");
        infoList.add(emailDetail);
        return infoList;
    }

    public static List<PhoneDetail> buildPhoneDetails() {
        List<PhoneDetail> phoneDetails = new ArrayList<>();
        PhoneDetail phoneDetail = new PhoneDetail();
        phoneDetail.setCountryCode("occaecat eiusmod v");
        phoneDetail.setPhoneType("Excepteur tempor");
        phoneDetail.setPhoneNumber("culpa");
        phoneDetail.setPreferred("pariatur aute si");
        phoneDetail.setRowStatus("fu");
        phoneDetails.add(phoneDetail);
        return phoneDetails;
    }

    public static List<Address> buildAddresses() {
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setAddressType("laboris quis");
        address.setResidingSince("1959-11-07T07:08:51.879Z");
        address.setPreferred("officias consect");
        address.setPostalCode("do consectetur");
        address.setCity("laborum nisi");
        address.setState("adipisicing cup");
        address.setCountry("occa");
        address.setAddressFormat("FREE_TEXT");
        address.setAddressLabel("mollit cillum");
        address.setLine1("deserunt ipsum voluptate adipisicing");
        address.setLine2("fugiat exercitation Duis sit aliquip");
        address.setLine3("sit est");
        address.setHouseNumber("Duis fugi");
        address.setBuildingLevel("labore d");
        address.setPremiseName("ullamco sed i");
        address.setStreetNumber("ea Lorem");
        address.setStreetName("");
        address.setLocality("aute non");
        address.setSuburb("deser");
        address.setTown("ullamco id aliqua");
        address.setRowStatus("sit est ");
        addresses.add(address);
        return addresses;
    }

    public static List<AlternateLanguage> buildAlternateLanguages() {
        List<AlternateLanguage> alternateLanguages = new ArrayList<>();
        AlternateLanguage alternateLanguage = new AlternateLanguage();
        alternateLanguage.setAttributeName("est");
        alternateLanguage.setLanguageCode("commodo Ut conseq");
        alternateLanguage.setDescription("cupidatat");
        alternateLanguages.add(alternateLanguage);
        return alternateLanguages;
    }

    public static List<IdentificationDocument> buildIdentificationDocuments() {
        List<IdentificationDocument> identificationDocuments = new ArrayList<>();
        IdentificationDocument identificationDocument = new IdentificationDocument();
        identificationDocument.setDocumentType("ad culpa do");
        identificationDocument.setDocumentNumber("aliquip");
        identificationDocument.setIssuingAuthority("minim officia in Duis");
        identificationDocument.setCountryOfIssue("com");
        identificationDocument.setPlaceOfIssue("Ut magna");
        identificationDocument.setIssueDate("1950-09-29T12:34:12.153Z");
        identificationDocument.setExpiryDate("1999-03-19T14:42:28.428Z");
        identificationDocument.setIdentificationType("ip");
        identificationDocument.setID("cillum");
        identificationDocument.setRowStatus("aliqu");
        identificationDocuments.add(identificationDocument);
        return identificationDocuments;
    }

    public static List<EmploymentDetail> buildEmploymentDetails() {
        List<EmploymentDetail> employmentDetails = new ArrayList<>();

        EmploymentDetail employmentDetail = new EmploymentDetail();
        employmentDetail.setNatureOfEmployment("voluptate q");
        employmentDetail.setEmploymentStatus("veniam est fugiat eu cillum");
        employmentDetail.setID("enim ");
        employmentDetail.setEmployer("qui enim");
        employmentDetail.setEmployerType("in");
        employmentDetail.setIndustry("consequat nisi amet tempor aute");
        employmentDetail.setCity("commodo proident ut");
        employmentDetail.setState("laboris pariatur sit esse");
        employmentDetail.setCountryOfDeputation("do");
        employmentDetail.setOccupation("est Excepteur laborum");
        employmentDetail.setDesignation("pariatur");
        employmentDetail.setEmploymentStartDate("2002-12-19T07:25:13.590Z");
        employmentDetail.setEmploymentEndDate("2018-01-20T09:55:41.224Z");
        employmentDetail.setRowStatus("et");
        employmentDetails.add(employmentDetail);
        return employmentDetails;
    }

    public static List<RetailIncome> buildRetailIncome() {
        List<RetailIncome> retailIncomes = new ArrayList<>();
        RetailIncome retailIncome = new RetailIncome();
        retailIncome.setIncomeType("des");
        retailIncome.setIncomeFrequency("ess");
        retailIncome.setCurrency("non");
        retailIncome.setIncomeAmount("nostrud eiusmod quis in");
        retailIncome.setRowStatus("");
        retailIncomes.add(retailIncome);
        return retailIncomes;
    }

    public static List<RetailExpense> buildRetailExpense() {
        List<RetailExpense> retailExpenses = new ArrayList<>();
        RetailExpense retailExpense = new RetailExpense();
        retailExpense.setExpenseType("culpa nostrud in");
        retailExpense.setExpenseFrequency("Lorem id");
        retailExpense.setCurrency("proident i");
        retailExpense.setExpenseAmount("culpa sint ea");
        retailExpense.setRowStatus("ipsu");
        retailExpenses.add(retailExpense);
        return retailExpenses;
    }

    public static List<BankStaff> buildBankStaffs() {
        List<BankStaff> bankStaffs = new ArrayList<>();
        BankStaff bankStaff = new BankStaff();
        bankStaff.setEmpDtlsApplicable("ullamco dolore e");
        bankStaff.setStaffIndicator("veniam");
        bankStaff.setEmpID("do deserunt aliq");
        bankStaff.setRelationship("eu do sit");
        bankStaff.setRowStatus("cons");
        bankStaffs.add(bankStaff);
        return bankStaffs;
    }

    public static List<CurrencyDetail> buildCurrencyDetails() {
        List<CurrencyDetail> currencyDetails = new ArrayList<>();
        CurrencyDetail currencyDetail = new CurrencyDetail();
        currencyDetail.setCcy("Lorem ");
        currencyDetail.setPrefentialExpiryDate("1962-09-29T22:58:47.716Z");
        currencyDetail.setCreditDiscountPercentage("incididunt quis ea dolore");
        currencyDetail.setDebitDiscountPercentage("ea ex Lorem voluptate do");
        currencyDetail.setWithholdingTaxPercentage("ex sed sunt magna");
        currencyDetail.setWithholdingTaxFloorLimit("aliqua dolore dolor Excepteur");
        currencyDetail.setRowStatus("aute ni");
        currencyDetails.add(currencyDetail);
        return currencyDetails;
    }

    public static CustomerRequestJSON buildRequestJsonFinacle() {
        CustomerRequestJSON requestFinacle = new CustomerRequestJSON();
        requestFinacle.setRelationshipmanagerInfo(buildRelationshipmanagerInfo());
        requestFinacle.setEmailDetails(buildEmailDetails());
        requestFinacle.setPhoneDetails(buildPhoneDetails());
        requestFinacle.setAddresses(buildAddresses());
        requestFinacle.setAlternateLanguages(buildAlternateLanguages());
        requestFinacle.setIdentificationDocuments(buildIdentificationDocuments());
        requestFinacle.setEmploymentDetails(buildEmploymentDetails());
        requestFinacle.setRetailIncome(buildRetailIncome());
        requestFinacle.setRetailExpense(buildRetailExpense());
        requestFinacle.setBankStaffs(buildBankStaffs());
        requestFinacle.setCurrencyDetails(buildCurrencyDetails());
        requestFinacle.setCifID("occaecat in pro");
        requestFinacle.setCustType("non ");
        requestFinacle.setShortName("sit in");
        requestFinacle.setSeniorCitizen("lab");
        requestFinacle.setTaxIDType("ut non aute nostrud");
        requestFinacle.setTaxIdentificationNumber("et aute eiusmod proident");
        requestFinacle.setTaxExemptionCode("dolore adipisicing laboru");
        requestFinacle.setTaxResident("enim eiusmod mollit ");
        requestFinacle.setFirstname("");
        requestFinacle.setMiddlename("");
        requestFinacle.setLastname("");
        requestFinacle.setMotherMaidenName("magna quis dolore");
        requestFinacle.setGender("culpa qui");
        requestFinacle.setRetailSegment("tempor ut sunt es");
        requestFinacle.setMaritalStatus("velit magn");
        requestFinacle.setCountryofPrimaryCitizenship("in");
        requestFinacle.setCifStatus("ipsum magna v");
        return requestFinacle;
    }


}
