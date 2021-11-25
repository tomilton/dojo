package co.com.nequi.usecase.createcustomer;

import co.com.nequi.model.customer.Customer;
import co.com.nequi.model.customer.CustomerRS;
import co.com.nequi.model.customer.LiteRegistryBrokerRQ;
import co.com.nequi.model.customer.LiteRegistryBrokerRS;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.exceptions.CastCustomerException;
import co.com.nequi.model.requestfinacle.customer.*;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.ErrorDetail;
import co.com.nequi.model.responsemdw.*;
import co.com.nequi.usecase.createcustomer.constant.Constant;
import co.com.nequi.usecase.createcustomer.helper.AbstractUseCase;
import co.com.nequi.usecase.createcustomer.util.BuildMessageUtil;
import co.com.nequi.usecase.util.UtilObject;
import co.com.nequi.usecase.util.UtilString;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.*;


/**
 * Name: Customer
 * Namespace: http://co.bancaDigital/nequi/services/UserServices/Customer/v1.0
 * Operation: createCustomer
 */
@RequiredArgsConstructor
public class CreateCustomerUseCase extends AbstractUseCase<ResponseMdw, RequestMdw> {

    private final CustomerServiceFinacle customerServiceFinacle;
    private final CustomerDefaultDataRepository defaultDataRepository;
    private Map<String, String> mapProperties = new HashMap<>();


    /**
     * Flujo principal
     *
     * @param requestMdw
     * @return
     */
    public Mono<ResponseMdw> execute(RequestMdw requestMdw) {
        /**
         * Datos de middleware, se pasan a Mono
         */
        Mono<Customer> customerMono = getCustomer(requestMdw);

        /**
         * Se consultan los datos por defecto
         */
        Mono<List<CustomerDefaultData>> defaultDataFlux = defaultDataRepository.getDefaultData("1").collectList();

        /**
         * Se combinan los datos por defecto y los datos del middleware para enviarselos a finacle
         */
        Mono<CustomerRequestFinacle> requestFinacleMono = customerMono
                .zipWith(defaultDataFlux).map(tuple -> buildRequestFinacle(tuple.getT1(), tuple.getT2()));

        /**
         * Respuesta de finacle
         */
        Mono<CustomerResponseFinacle> requestFinacle = requestFinacleMono.flatMap(customerServiceFinacle::save);

        /**
         * Se retorna la respuesta a middleware
         */
        return requestFinacle.flatMap(responseFinacle -> handleResponseFinacle(responseFinacle, requestMdw)
        ).onErrorResume(error -> handleErrors(error, requestMdw));
    }

    /**
     * Retorna un Mono del customer enviado por parametro.
     *
     * @param requestMdw
     * @return
     */
    private Mono<Customer> getCustomer(RequestMdw requestMdw) {
        Customer customer = (Customer) requestMdw.getRequestHeaderOut().getBody().getAny();
        if (customer.getLiteRegistryBrokerRQ() == null
                || customer.getLiteRegistryBrokerRQ().getPersonalInfo() == null) {
            return Mono.error(new CastCustomerException("Error de cast al recibir customer"));
        }
        return Mono.just(customer);
    }

    /**
     * Hace el manejo de la respuesta de finacle.
     * En caso de error se debe revisar <ErrorDetail> notifica a middleware.
     * En caso de exito se envia en el any la respuesta.
     *
     * @param responseFinacle
     * @param requestMdw
     * @return
     */
    private Mono<ResponseMdw> handleResponseFinacle(CustomerResponseFinacle responseFinacle, RequestMdw requestMdw) {
        List<ErrorDetail> errorDetails = responseFinacle.getMeta().getErrorDetails();
        if (errorDetails.isEmpty()) {
            CustomerRS customerRS = new CustomerRS();
            LiteRegistryBrokerRS liteRegistryBrokerRS = new LiteRegistryBrokerRS();
            liteRegistryBrokerRS.setCifId(responseFinacle.getData().getCifID());
            customerRS.setLiteRegistryBrokerRS(liteRegistryBrokerRS);
            return Mono.just(this.buildResponseSucces(customerRS, requestMdw));
        } else {
            return Mono.just(this.buildResponseWithError(requestMdw, errorDetails.toString()));
        }
    }

    /**
     * Captura los errores, estos pueden venir desde el casteo, defaultdata, finacle, negocio
     *
     * @param error
     * @param requestMdw
     * @return
     */
    private Mono<ResponseMdw> handleErrors(Throwable error, RequestMdw requestMdw) {
        return Mono.just(this.buildResponseWithError(requestMdw, error.getMessage()));
    }

    /**
     * Construye la respuesta para notificarle a middleware
     *
     * @param any
     * @param requestMdw
     * @return
     */
    private ResponseMdw buildResponseSucces(Object any, RequestMdw requestMdw) {
        Destination destination = BuildMessageUtil.buildDestination(
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getName(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getNamespace(),
                requestMdw.getRequestHeaderOut().getHeader().getDestination().getOperation());
        ResponseStatus responseStatus = BuildMessageUtil.buildStatus(Constant.COMMON_STRING_ZERO,
                Constant.COMMON_STRING_SUCCESS, "", "");
        Header header = BuildMessageUtil.buildHeader(requestMdw.getRequestHeaderOut().getHeader().getSystemID(),
                requestMdw.getRequestHeaderOut().getHeader().getMessageID(),
                requestMdw.getRequestHeaderOut().getHeader().getInvokerDateTime(), destination, responseStatus);
        ResponseHeaderOut responseHeaderOut = BuildMessageUtil.buildResponseHeaderOut(header, any);
        return BuildMessageUtil.buildResponse(responseHeaderOut, Constant.COMMON_STRING_YES);
    }

    /**
     * Construye la respuesta con el error para notificarle a middleware
     *
     * @param requestMdw
     * @param error
     * @return
     */
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

    /**
     * Fija los datos para hacer la peticion a finacle
     *
     * @param customer
     * @param defaultData
     * @return
     */
    private CustomerRequestFinacle buildRequestFinacle(Customer customer, List<CustomerDefaultData> defaultData) {
        LiteRegistryBrokerRQ middleware = customer.getLiteRegistryBrokerRQ();
        middleware.getPersonalInfo().validarIdNumber();
        CustomerRequestFinacle requestFinacle = new CustomerRequestFinacle();
        defaultData.forEach(item -> mapProperties.put(item.getNombre(), item.getValorDefecto()));
        requestFinacle.setCifStatus(mapProperties.get("CustStatus"));
        requestFinacle.setCustType(mapProperties.get("CustType"));
        requestFinacle.setDob(getDob(requestFinacle, middleware));
        requestFinacle.setShortName(mapProperties.get("ShortName"));
        requestFinacle.setMinor(mapProperties.get("CustMinorInd"));
        requestFinacle.setTaxResident(mapProperties.get("NRCustInd"));
        requestFinacle.setFirstname(getFirstName(requestFinacle, middleware));
        requestFinacle.setMiddlename(getMiddleName(requestFinacle, middleware));
        requestFinacle.setLastname(getLastName(requestFinacle, middleware));
        requestFinacle.setMotherMaidenName(getMotherMaidenName(requestFinacle, middleware));
        requestFinacle.setGender(mapProperties.get("Gender"));
        requestFinacle.setRelStartDate(new Date().toString());
        requestFinacle.setRetailSegment(mapProperties.get("SegmentationClass"));
        requestFinacle.setSaluation(mapProperties.get("TitlePrefix"));
        requestFinacle.setPrimaryBranch(mapProperties.get("PrimaryServiceCenter"));
        requestFinacle.setMaritalStatus(mapProperties.get("MaritalStatus"));
        requestFinacle.setCountryofPrimaryCitizenship("Nationality");
        requestFinacle.setCifID("non ja");
        requestFinacle.setSeniorCitizen("lab");
        requestFinacle.setTaxIDType("ut non aute nostrud");
        requestFinacle.setTaxIdentificationNumber("et aute eiusmod proident");
        requestFinacle.setTaxExemptionCode("dolore adipisicing laboru");
        buildListsFinacle(requestFinacle, middleware);
        return requestFinacle;
    }

    /**
     * Fija los objetos de tipo lista para hacer la peticion a finacle
     *
     * @param requestFinacle
     * @param middleware
     */
    private void buildListsFinacle(final CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ middleware) {
        requestFinacle.setRelationshipmanagerInfo(buildRelationshipmanagerInfo());
        requestFinacle.setEmailDetails(buildEmailDetails(middleware));
        requestFinacle.setPhoneDetails(buildPhoneDetails(middleware));
        requestFinacle.setAddresses(buildAddresses(middleware));
        requestFinacle.setAlternateLanguages(buildAlternateLanguages());
        requestFinacle.setIdentificationDocuments(buildIdentificationDocuments(middleware));
        requestFinacle.setEmploymentDetails(buildEmploymentDetails(middleware));
        requestFinacle.setRetailIncome(buildRetailIncome());
        requestFinacle.setRetailExpense(buildRetailExpense());
        requestFinacle.setBankStaffs(buildBankStaffs());
        requestFinacle.setCurrencyDetails(buildCurrencyDetails());
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param requestFinacle
     * @param middleware
     * @return
     */
    private String getFirstName(CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ middleware) {
        String name1 = middleware.getPersonalInfo().getName1();
        if (UtilString.cadenaVacia(requestFinacle.getFirstname()) && !UtilString.cadenaVacia(name1)) {
            return name1;
        } else {
            return mapProperties.get("FirstName");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param requestFinacle
     * @param liteRegistryBrokerRQ
     * @return
     */
    private String getMotherMaidenName(CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ liteRegistryBrokerRQ) {
        String motherMaidenName = liteRegistryBrokerRQ.getPersonalInfo().getLastName2();
        if (UtilString.cadenaVacia(requestFinacle.getMotherMaidenName()) && !UtilString.cadenaVacia(motherMaidenName)) {
            return motherMaidenName;
        } else {
            return mapProperties.get("MotherMaidenName");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param requestFinacle
     * @param liteRegistryBrokerRQ
     * @return
     */
    private String getDob(CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ liteRegistryBrokerRQ) {
        String birthDate = liteRegistryBrokerRQ.getPersonalInfo().getBirthDate();
        if (UtilString.cadenaVacia(requestFinacle.getDob()) && !UtilString.cadenaVacia(birthDate)) {
            return birthDate;
        } else {
            return "T00:00:00.000";
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param requestFinacle
     * @param liteRegistryBrokerRQ
     * @return
     */
    private String getLastName(CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ liteRegistryBrokerRQ) {
        String lastName = liteRegistryBrokerRQ.getPersonalInfo().getLastName1();
        if (UtilString.cadenaVacia(requestFinacle.getLastname()) && !UtilString.cadenaVacia(lastName)) {
            return lastName;
        } else {
            return mapProperties.get("LastName");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param requestFinacle
     * @param liteRegistryBrokerRQ
     * @return
     */
    private String getMiddleName(CustomerRequestFinacle requestFinacle, LiteRegistryBrokerRQ liteRegistryBrokerRQ) {
        String name2 = liteRegistryBrokerRQ.getPersonalInfo().getName2();
        if (UtilString.cadenaVacia(requestFinacle.getMiddlename()) && !UtilString.cadenaVacia(name2)) {
            return name2;
        } else {
            return mapProperties.get("MiddleName");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param middleware
     * @return
     */
    private String getCityAddress(LiteRegistryBrokerRQ middleware) {
        if (UtilObject.isNotNull(middleware.getPersonalInfo().getCity())) {
            return middleware.getPersonalInfo().getCity().toString();
        } else {
            return mapProperties.get("City");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param middleware
     * @return
     */
    private String getStateAddress(LiteRegistryBrokerRQ middleware) {
        String state = middleware.getPersonalInfo().getState();
        if (!UtilString.cadenaVacia(state)) {
            return state;
        } else {
            return mapProperties.get("State");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param middleware
     * @return
     */
    private String getAddressLabel(LiteRegistryBrokerRQ middleware) {
        String addresLabel = middleware.getPersonalInfo().getAddress();
        if (!UtilString.cadenaVacia(addresLabel)) {
            return addresLabel;
        } else {
            return mapProperties.get("AddressLabel");
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param middleware
     * @return
     */
    private String getDocumentNumber(LiteRegistryBrokerRQ middleware) {
        String idNumber = middleware.getPersonalInfo().getIDNumber();
        if (!UtilString.cadenaVacia(idNumber)) {
            return idNumber;
        } else {
            return "";
        }
    }

    /**
     * Valida el dato , antes de fijarlo a la propiedad para finacle, si esta vacio envia el valor por defecto
     *
     * @param middleware
     * @return
     */
    private String getDocumentType(LiteRegistryBrokerRQ middleware) {
        String typeID = middleware.getPersonalInfo().getTypeID();
        if (!UtilString.cadenaVacia(typeID)) {
            return typeID;
        } else {
            return "";
        }
    }

    /**
     * Construye la lista de RelationshipmanagerInfo para enviar a finacle
     *
     * @return List<RelationshipmanagerInfo>
     */
    private List<RelationshipmanagerInfo> buildRelationshipmanagerInfo() {
        List<RelationshipmanagerInfo> infoList = new ArrayList<>();
        infoList.add(RelationshipmanagerInfo.builder()
                .relationshipManagerID("officia consec")
                .relationshipManagerName("dolor mollit")
                .primary("in enim id").department("mollit Ut ipsum")
                .rowStatus(Constant.ROW_STATUS_ADDED).build());
        return infoList;
    }

    /**
     * Construye la lista de EmailDetail para enviar a finacle
     *
     * @param middleware
     * @return
     */
    private List<EmailDetail> buildEmailDetails(LiteRegistryBrokerRQ middleware) {
        List<EmailDetail> infoList = new ArrayList<>();
        infoList.add(EmailDetail.builder()
                .emailID(middleware.getPersonalInfo().getEmail())
                .emailType(mapProperties.get("Value.PhoneEmailInfo.EMAIL"))
                .preferred(mapProperties.get("PreferredEmailYes"))
                .rowStatus(mapProperties.get("Operation.PhoneEmailInfo.EMAIL"))
                .build());
        return infoList;
    }

    /**
     * Construye la lista de PhoneDetail para enviar a finacle
     *
     * @param middleware
     * @return
     */
    private List<PhoneDetail> buildPhoneDetails(LiteRegistryBrokerRQ middleware) {
        List<PhoneDetail> phoneDetails = new ArrayList<>();
        phoneDetails.add(PhoneDetail.builder()
                .countryCode(mapProperties.get("PhoneNumCountryCode"))
                .phoneType(mapProperties.get("PhoneOrEmail.PhoneEmailInfo.PHONE"))
                .phoneNumber(middleware.getDeviceInfo().getPhoneNumber())
                .preferred(mapProperties.get("PreferredPhoneYes"))
                .rowStatus(mapProperties.get("Operation.PhoneEmailInfo.PHONE"))
                .build()
        );
        return phoneDetails;
    }

    /**
     * Construye la lista de Address para enviar a finacle
     *
     * @param middleware
     * @return
     */
    private List<Address> buildAddresses(LiteRegistryBrokerRQ middleware) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder()
                .addressType("")
                .residingSince("")
                .preferred(mapProperties.get("PreferredAddressYes"))
                .postalCode(mapProperties.get("PostalCode"))
                .city(getCityAddress(middleware))
                .state(getStateAddress(middleware))
                .country(mapProperties.get("Country"))
                .addressFormat(mapProperties.get("AddrFormat"))
                .addressLabel(getAddressLabel(middleware))
                .line1("")
                .line2("")
                .line3("")
                .houseNumber("")
                .buildingLevel("")
                .premiseName("")
                .streetNumber("")
                .streetName("")
                .locality("")
                .suburb("")
                .town("")
                .rowStatus(mapProperties.get("Operation.AddrType"))
                .build());
        return addresses;
    }

    /**
     * Construye la lista de AlternateLanguage para enviar a finacle
     *
     * @return List<AlternateLanguage>
     */
    private List<AlternateLanguage> buildAlternateLanguages() {
        List<AlternateLanguage> alternateLanguages = new ArrayList<>();
        alternateLanguages.add(AlternateLanguage.builder()
                .attributeName("est")
                .languageCode("commodo Ut conseq")
                .description("cupidatat")
                .build());
        return alternateLanguages;
    }

    /**
     * Construye la lista de IdentificationDocument para enviar a finacle
     *
     * @param middleware
     * @return List<IdentificationDocument>
     */
    private List<IdentificationDocument> buildIdentificationDocuments(LiteRegistryBrokerRQ middleware) {
        List<IdentificationDocument> identificationDocuments = new ArrayList<>();
        identificationDocuments.add(
                IdentificationDocument.builder()
                        .documentType(getDocumentType(middleware))
                        .documentNumber(getDocumentNumber(middleware))
                        .issuingAuthority("")
                        .countryOfIssue("")
                        .placeOfIssue("")
                        .issueDate("")
                        .expiryDate("")
                        .identificationType(mapProperties.get("Value.IdentificationType"))
                        .id("")
                        .rowStatus(mapProperties.get("Operation.IdentificationType"))
                        .build()
        );
        return identificationDocuments;
    }


    /**
     * Construye la lista de EmploymentDetail para enviar a finacle
     *
     * @return
     */
    private List<EmploymentDetail> buildEmploymentDetails(LiteRegistryBrokerRQ middleware) {
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
                        .occupation(middleware.getPersonalInfo().getOccupation())
                        .designation("pariatur")
                        .employmentStartDate("2002-12-19T07:25:13.590Z")
                        .employmentEndDate("2018-01-20T09:55:41.224Z")
                        .rowStatus("et")
                        .build()
        );
        return employmentDetails;
    }

    /**
     * Construye la lista de RetailIncome para enviar a finacle
     *
     * @return
     */
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

    /**
     * Construye la lista de RetailExpense para enviar a finacle
     *
     * @return
     */
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

    /**
     * Construye la lista de BankStaff para enviar a finacle
     *
     * @return
     */
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

    /**
     * Construye la lista de CurrencyDetail para enviar a finacle
     *
     * @return
     */
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