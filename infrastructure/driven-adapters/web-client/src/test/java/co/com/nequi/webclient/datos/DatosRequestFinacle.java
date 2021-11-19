package co.com.nequi.webclient.datos;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.requestfinacle.customer.*;

import java.util.ArrayList;
import java.util.List;

public class DatosRequestFinacle {

    private DatosRequestFinacle() {
    }

    public static List<RelationshipmanagerInfo> buildRelationshipmanagerInfo() {
        List<RelationshipmanagerInfo> infoList = new ArrayList<>();
        infoList.add(RelationshipmanagerInfo.builder()
                .relationshipManagerID("officia consec")
                .relationshipManagerName("dolor mollit")
                .primary("in enim id").department("mollit Ut ipsum")
                .rowStatus("m").build());
        return infoList;
    }

    public static List<EmailDetail> buildEmailDetails() {
        List<EmailDetail> infoList = new ArrayList<>();
        infoList.add(EmailDetail.builder().emailID("labo").emailType("in officia anim D")
                .preferred("sit").rowStatus("ull").build());
        return infoList;
    }

    public static List<PhoneDetail> buildPhoneDetails() {
        List<PhoneDetail> phoneDetails = new ArrayList<>();
        phoneDetails.add(PhoneDetail.builder().countryCode("occaecat eiusmod v")
                .phoneType("Excepteur tempor").phoneNumber("culpa")
                .preferred("pariatur aute si").rowStatus("fu").build()
        );
        return phoneDetails;
    }

    public static List<Address> buildAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder().addressType("laboris quis").
                residingSince("1959-11-07T07:08:51.879Z")
                .preferred("officias consect")
                .postalCode("do consectetur")
                .city("laborum nisi")
                .state("adipisicing cup")
                .country("occa")
                .addressFormat("FREE_TEXT")
                .addressLabel("mollit cillum")
                .line1("deserunt ipsum voluptate adipisicing")
                .line2("fugiat exercitation Duis sit aliquip")
                .line3("sit est")
                .houseNumber("Duis fugi")
                .buildingLevel("labore d")
                .premiseName("ullamco sed i")
                .streetNumber("ea Lorem")
                .streetName("")
                .locality("aute non")
                .suburb("deser")
                .town("ullamco id aliqua")
                .rowStatus("sit est ")
                .build());
        return addresses;
    }

    public static List<AlternateLanguage> buildAlternateLanguages() {
        List<AlternateLanguage> alternateLanguages = new ArrayList<>();
        alternateLanguages.add(AlternateLanguage.builder()
                .attributeName("est")
                .languageCode("commodo Ut conseq")
                .description("cupidatat")
                .build());
        return alternateLanguages;
    }

    public static List<IdentificationDocument> buildIdentificationDocuments() {
        List<IdentificationDocument> identificationDocuments = new ArrayList<>();
        identificationDocuments.add(
                IdentificationDocument.builder()
                        .documentType("ad culpa do")
                        .documentNumber("aliquip")
                        .issuingAuthority("minim officia in Duis")
                        .countryOfIssue("com")
                        .placeOfIssue("Ut magna")
                        .issueDate("1950-09-29T12:34:12.153Z")
                        .expiryDate("1999-03-19T14:42:28.428Z")
                        .identificationType("ip")
                        .id("cillum")
                        .rowStatus("aliqu")
                        .build()
        );
        return identificationDocuments;
    }

    public static List<EmploymentDetail> buildEmploymentDetails() {
        List<EmploymentDetail> employmentDetails = new ArrayList<>();
        employmentDetails.add(
                EmploymentDetail.builder()
                        .natureOfEmployment("voluptate q")
                        .employmentStatus("veniam est fugiat eu cillum")
                        .id("enim ")
                        .employer("qui enim")
                        .employerType("in")
                        .industry("consequat nisi amet tempor aute")
                        .city("commodo proident ut")
                        .state("laboris pariatur sit esse")
                        .countryOfDeputation("do")
                        .occupation("est Excepteur laborum")
                        .designation("pariatur")
                        .employmentStartDate("2002-12-19T07:25:13.590Z")
                        .employmentEndDate("2018-01-20T09:55:41.224Z")
                        .rowStatus("et")
                        .build()
        );
        return employmentDetails;
    }

    public static List<RetailIncome> buildRetailIncome() {
        List<RetailIncome> retailIncomes = new ArrayList<>();
        retailIncomes.add(
                RetailIncome.builder()
                        .incomeType("des")
                        .incomeFrequency("ess")
                        .currency("non")
                        .incomeAmount("nostrud eiusmod quis in")
                        .rowStatus("")
                        .build()
        );
        return retailIncomes;
    }

    public static List<RetailExpense> buildRetailExpense() {
        List<RetailExpense> retailExpenses = new ArrayList<>();
        retailExpenses.add(
                RetailExpense.builder().
                        expenseType("culpa nostrud in")
                        .expenseFrequency("Lorem id")
                        .currency("proident i")
                        .expenseAmount("culpa sint ea")
                        .rowStatus("ipsu")
                        .build()
        );
        return retailExpenses;
    }

    public static List<BankStaff> buildBankStaffs() {
        List<BankStaff> bankStaffs = new ArrayList<>();
        bankStaffs.add(
                BankStaff.builder()
                        .empDtlsApplicable("ullamco dolore e")
                        .staffIndicator("veniam")
                        .empID("do deserunt aliq")
                        .relationship("eu do sit")
                        .rowStatus("cons")
                        .build()
        );
        return bankStaffs;
    }

    public static List<CurrencyDetail> buildCurrencyDetails() {
        List<CurrencyDetail> currencyDetails = new ArrayList<>();
        currencyDetails.add(
                CurrencyDetail.builder()
                        .ccy("Lorem ")
                        .prefentialExpiryDate("1962-09-29T22:58:47.716Z")
                        .creditDiscountPercentage("incididunt quis ea dolore")
                        .debitDiscountPercentage("ea ex Lorem voluptate do")
                        .withholdingTaxPercentage("ex sed sunt magna")
                        .withholdingTaxFloorLimit("aliqua dolore dolor Excepteur")
                        .rowStatus("aute ni")
                        .build()
        );
        return currencyDetails;
    }

    public static CustomerRequestFinacle buildRequestFinacle(Customer customer) {
        CustomerRequestFinacle requestFinacle = new CustomerRequestFinacle();
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
        requestFinacle.setFirstname(customer.getLiteRegistryBrokerRQ().getPersonalInfo().getName1());
        requestFinacle.setMiddlename(customer.getLiteRegistryBrokerRQ().getPersonalInfo().getName2());
        requestFinacle.setLastname(customer.getLiteRegistryBrokerRQ().getPersonalInfo().getLastName1());
        requestFinacle.setMotherMaidenName("magna quis dolore");
        requestFinacle.setGender("culpa qui");
        requestFinacle.setRetailSegment("tempor ut sunt es");
        requestFinacle.setMaritalStatus("velit magn");
        requestFinacle.setCountryofPrimaryCitizenship("in");
        requestFinacle.setCifStatus("ipsum magna v");
        return requestFinacle;
    }


}
