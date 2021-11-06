package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.requestfinacle.customer.*;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerServiceFinacle customerServiceFinacle;

    public Mono<ResponseMdw> createCustomer(RequestMdw requestMdw) {
        ResponseMdw responseMdw;
        try {

            Mono<CustomerResponseFinacle> responseFinacle = customerServiceFinacle.save(buildRequestFinacle());

            responseFinacle.flatMap(finacle -> {
                CustomerResponseFinacle customerResponseFinacle = finacle;
                return Mono.just(finacle);
            }).onErrorResume(error -> {
                System.out.println(error);
                return Mono.error(error);
            }).subscribe();

            Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();
            customer.getLiteRegistryBrokerRQ().getPersonalInfo().validarIdNumber();
            /**
             * Se construye el HEADER
             */
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                    Constant.COMMON_STRING_SUCCESS, "", "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, customer);
            responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
            return Mono.just(responseMdw);

        } catch (CreateCustomerException runtimeException) {
            /**
             * Se construye el HEADER con error
             */
            Destination destination = BuildMessageUtil.buildDestination(
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                    requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
            ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.ERROR_GENERIC_CODE,
                    Constant.COMMON_STRING_ERROR_GENERIC, runtimeException.getMessage(), "");
            Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                    requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                    requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
            ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, "");
            responseMdw = BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
            return Mono.just(responseMdw);
        }
    }


    private List<RelationshipmanagerInfo> buildRelationshipmanagerInfo() {
        List<RelationshipmanagerInfo> infoList = new ArrayList<>();
        infoList.add(RelationshipmanagerInfo.builder()
                .relationshipManagerID("officia consec")
                .relationshipManagerName("dolor mollit")
                .primary("in enim id").department("mollit Ut ipsum")
                .rowStatus("m").build());
        return infoList;
    }

    private List<EmailDetail> buildEmailDetails() {
        List<EmailDetail> infoList = new ArrayList<>();
        infoList.add(EmailDetail.builder().emailID("labo").emailType("in officia anim D")
                .preferred("sit").rowStatus("ull").build());
        return infoList;
    }

    private List<PhoneDetail> buildPhoneDetails() {
        List<PhoneDetail> phoneDetails = new ArrayList<>();
        phoneDetails.add(PhoneDetail.builder().countryCode("occaecat eiusmod v")
                .phoneType("Excepteur tempor").phoneNumber("culpa")
                .preferred("pariatur aute si").rowStatus("fu").build()
        );
        return phoneDetails;
    }

    private List<Address> buildAddresses() {
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

    private List<AlternateLanguage> buildAlternateLanguages() {
        List<AlternateLanguage> alternateLanguages = new ArrayList<>();
        alternateLanguages.add(AlternateLanguage.builder()
                .attributeName("est")
                .languageCode("commodo Ut conseq")
                .description("cupidatat")
                .build());
        return alternateLanguages;
    }

    private List<IdentificationDocument> buildIdentificationDocuments() {
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

    private List<EmploymentDetail> buildEmploymentDetails() {
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

    private List<RetailIncome> buildRetailIncome() {
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

    private List<RetailExpense> buildRetailExpense() {
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

    private List<BankStaff> buildBankStaffs() {
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

    private List<CurrencyDetail> buildCurrencyDetails() {
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

    private CustomerRequestFinacle buildRequestFinacle() {
        return CustomerRequestFinacle.builder()
                .relationshipmanagerInfo(buildRelationshipmanagerInfo())
                .emailDetails(buildEmailDetails())
                .phoneDetails(buildPhoneDetails())
                .addresses(buildAddresses())
                .alternateLanguages(buildAlternateLanguages())
                .identificationDocuments(buildIdentificationDocuments())
                .employmentDetails(buildEmploymentDetails())
                .retailIncome(buildRetailIncome())
                .retailExpense(buildRetailExpense())
                .bankStaffs(buildBankStaffs())
                .currencyDetails(buildCurrencyDetails())
                .cifID("occaecat in pro")
                .custType("non ")
                .shortName("sit in")
                .seniorCitizen("lab")
                .taxIDType("ut non aute nostrud")
                .taxIdentificationNumber("et aute eiusmod proident")
                .taxExemptionCode("dolore adipisicing laboru")
                .taxResident("enim eiusmod mollit ")
                .firstname("sed occaecat sint anim")
                .middlename("adipisicing nulla")
                .lastname("eu do")
                .motherMaidenName("magna quis dolore")
                .gender("culpa qui")
                .retailSegment("tempor ut sunt es")
                .maritalStatus("velit magn")
                .countryofPrimaryCitizenship("in")
                .cifStatus("ipsum magna v")
                .build();
    }


}
