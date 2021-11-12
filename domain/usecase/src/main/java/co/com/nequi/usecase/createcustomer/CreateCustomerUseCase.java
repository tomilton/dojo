package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.LiteRegistryBrokerRQ;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customer.gateways.LoggerCustomer;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.CreateCustomerException;
import co.com.nequi.model.exceptions.CreateCustomerFinacleException;
import co.com.nequi.model.requestfinacle.customer.*;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.ErrorDetail;
import co.com.nequi.model.responsefinacle.customer.Meta;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import co.com.nequi.model.exceptions.DefaultDataException;
import co.com.nequi.usecase.createcustomer.util.UtilString;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerServiceFinacle customerServiceFinacle;

    private final LoggerCustomer loggerCustomer;

    private final CustomerDefaultDataRepository defaultDataRepository;

    public Mono<ResponseMdw> createCustomer(RequestMdw requestMdw) {

        Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();
        customer.getLiteRegistryBrokerRQ().getPersonalInfo().validarIdNumber();

        return defaultDataRepository.getDefaultData("1").collectList()
                .doOnNext(next -> {
                    loggerCustomer.info(".::TRAZANDO DATOS POR DEFECTO::.");
                })
                .doOnError(de -> loggerCustomer.info("Error defult data: " + de.getMessage()))
                .flatMap(defaultData -> {
                    CustomerRequestFinacle requestFinacle = new CustomerRequestFinacle();
                    buildRequestFinacle(requestFinacle, customer, defaultData);
                    loggerCustomer.info(".::DATOS CUSTOMER::.");
                    return Mono.just(requestFinacle);
                }).flatMap(customerFinal -> {
                    Mono<CustomerResponseFinacle> responseFinacleMono = customerServiceFinacle.save(customerFinal);
                    loggerCustomer.info(".::SOLICITUD FINACLE::.");
                    return responseFinacleMono;
                })
                .onErrorResume(e -> {
                    if (e instanceof CreateCustomerFinacleException) {
                        loggerCustomer.info("CreateCustomerFinacleException: " + e.getMessage());
                    }
                    if (e instanceof DefaultDataException) {
                        loggerCustomer.info("CreateCustomerFinacleException: " + e.getMessage());
                    }
                    return Mono.just(buildErrorFinacle("", e.getMessage()));
                })
                .flatMap(responseFinacle -> {
                    loggerCustomer.info(".::RESPUESTA FINACLE::.");
                    List<ErrorDetail> errorDetails = responseFinacle.getMeta().getErrorDetails();
                    if (errorDetails.isEmpty()) {
                        return Mono.just(this.buildResponseSucces(responseFinacle.getData().getCifID(), requestMdw));
                    } else {
                        return Mono.just(this.buildResponseWithError(requestMdw, errorDetails.toString()));
                    }
                });
    }


    private CustomerResponseFinacle buildErrorFinacle(String errorcode, String errordesc) {
        CustomerResponseFinacle customerResponseFinacle = new CustomerResponseFinacle();
        customerResponseFinacle.setMeta(new Meta());
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorcode(errorcode);
        errorDetail.setErrordesc(errordesc);
        customerResponseFinacle.getMeta().setErrorDetails(new ArrayList<>());
        customerResponseFinacle.getMeta().getErrorDetails().add(errorDetail);
        return customerResponseFinacle;
    }

    private void buildRequestFinacle(CustomerRequestFinacle requestFinacle, Customer customer, List<CustomerDefaultData> defaultData) {
        LiteRegistryBrokerRQ liteRegistryBrokerRQ = customer.getLiteRegistryBrokerRQ();
        defaultData.forEach(item -> {
            switch (item.getNombre()) {
                case "CustStatus":
                    requestFinacle.setCifStatus(item.getValorDefecto());
                    break;
                case "CustType":
                    requestFinacle.setCustType(item.getValorDefecto());
                    break;
                case "BirthDt":
                    String birthDate = liteRegistryBrokerRQ.getPersonalInfo().getBirthDate();
                    if (UtilString.cadenaVacia(birthDate)) {
                        requestFinacle.setDob("T00:00:00.000");
                    } else {
                        requestFinacle.setDob(birthDate);
                    }
                    break;
                case "ShortName":
                    requestFinacle.setShortName(item.getValorDefecto());
                    break;
                case "CustMinorInd":
                    requestFinacle.setMinor(item.getValorDefecto());
                    break;
                case "NRCustInd":
                    requestFinacle.setTaxResident(item.getValorDefecto());
                    break;
                case "FirstName":
                    requestFinacle.setFirstname(item.getValorDefecto());
                    break;
                case "MiddleName":
                    String name2 = liteRegistryBrokerRQ.getPersonalInfo().getName2();
                    if (UtilString.cadenaVacia(name2)) {
                        requestFinacle.setMiddlename("");
                    } else {
                        requestFinacle.setMiddlename(item.getValorDefecto());
                    }
                    break;
                case "LastName":
                    String lastName = liteRegistryBrokerRQ.getPersonalInfo().getLastName1();
                    if (UtilString.cadenaVacia(lastName)) {
                        requestFinacle.setLastname(lastName);
                    } else {
                        requestFinacle.setLastname(item.getValorDefecto());
                    }
                    break;
                case "MotherMaidenName":
                    String motherMaidenName = liteRegistryBrokerRQ.getPersonalInfo().getLastName2();
                    if (UtilString.cadenaVacia(motherMaidenName)) {
                        requestFinacle.setMotherMaidenName("");
                    } else {
                        requestFinacle.setMiddlename(motherMaidenName);
                    }
                    break;
                case "Gender":
                    requestFinacle.setGender(item.getValorDefecto());
                    break;
                case "RelationshipOpeningDt":
                    requestFinacle.setRelStartDate(new Date().toString());
                    break;
                case "SegmentationClass":
                    requestFinacle.setRetailSegment(item.getValorDefecto());
                    break;
                case "TitlePrefix":
                    requestFinacle.setSaluation(item.getValorDefecto());
                    break;
                case "PrimaryServiceCenter":
                    requestFinacle.setPrimaryBranch(item.getValorDefecto());
                    break;
                case "MaritalStatus":
                    requestFinacle.setMaritalStatus(item.getValorDefecto());
                    break;
                case "Nationality":
                    requestFinacle.setCountryofPrimaryCitizenship(item.getValorDefecto());
                    break;
                default:
            }
        });
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
        requestFinacle.setCifID("non ja");
        requestFinacle.setSeniorCitizen("lab");
        requestFinacle.setTaxIDType("ut non aute nostrud");
        requestFinacle.setTaxIdentificationNumber("et aute eiusmod proident");
        requestFinacle.setTaxExemptionCode("dolore adipisicing laboru");
    }

    private ResponseMdw buildResponseSucces(String cifId, RequestMdw requestMdw) {
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                Constant.COMMON_STRING_SUCCESS, "", "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, cifId);
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
    }

    private ResponseMdw buildResponseWithError(RequestMdw requestMdw, String error) {
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.ERROR_GENERIC_CODE,
                Constant.COMMON_STRING_ERROR_GENERIC, error, "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, "");
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
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


}
