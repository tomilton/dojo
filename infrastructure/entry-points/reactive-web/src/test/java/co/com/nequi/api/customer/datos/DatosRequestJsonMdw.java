package co.com.nequi.api.customer.datos;


import co.com.nequi.api.models.createcustomer.*;
import co.com.nequi.api.requestmdw.*;

import java.util.ArrayList;
import java.util.List;

public class DatosRequestJsonMdw {

    private DatosRequestJsonMdw() {
    }

    public static Destination buildDestination() {
        Destination destination = new Destination();
        destination.setName("Customer");
        destination.setNamespace("http://co.bancaDigital/nequi/services/UserServices/Customer/v1.0");
        destination.setOperation("createCustomer");
        return destination;
    }

    public static SecurityCredential buildSecurityCredential() {
        SecurityCredential securityCredential = new SecurityCredential();
        securityCredential.setUserName("BROKER");
        securityCredential.setUserToken("TOKEN");
        return securityCredential;
    }

    public static Header buildHeader() {
        Header header = new Header();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setSecurityCredential(buildSecurityCredential());
        header.setDestination(buildDestination());
        header.setMessageContext(buildMessageContext());
        return header;
    }

    public static MessageContext buildMessageContext() {
        MessageContext messageContext = new MessageContext();
        messageContext.setProperty(buildProperty());
        return messageContext;
    }

    public static Property buildProperty() {
        Property property = new Property();
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setKey("RQ");
        item1.setValue("liteRegistryBrokerRQ");
        items.add(item1);
        Item item2 = new Item();
        item2.setKey("RS");
        item2.setValue("liteRegistryBrokerRS");
        items.add(item2);
        Item item3 = new Item();
        item3.setKey("Region");
        item3.setValue("C001");
        items.add(item3);
        Item item4 = new Item();
        item4.setKey("appType");
        item4.setValue("NequiApp");
        items.add(item4);
        property.setItem(items);
        return property;
    }

    public static DeviceInfo buildDeviceInfo() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setPhoneNumber("69029400");
        deviceInfo.setDeviceID("488e6df9b9372927");
        deviceInfo.setPassword("##HIDDEN_DATA##");
        return deviceInfo;
    }

    public static DemographicInfo buildDemographicInfo() {
        DemographicInfo demographicInfo = new DemographicInfo();
        demographicInfo.setGrossIncome("250");
        demographicInfo.setEmploymentType("Employed");
        demographicInfo.setEmpType("CURRENT_EMPLOYMENT");
        return demographicInfo;
    }

    public static PersonalInfo buildPersonalInfo() {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName1("JOSE");
        personalInfo.setName2("CARLOS");
        personalInfo.setLastName1("GONZALEZ");
        personalInfo.setLastName2("LEZCANO");
        personalInfo.setIDNumber("8-861-2488");
        personalInfo.setSecondIDNumber(new City());
        personalInfo.setExpeditionDate(new City());
        personalInfo.setBirthDate("1992-07-31");
        personalInfo.setEmail("josecarg31@gmail.com");
        personalInfo.setNickName(new City());
        personalInfo.setTypeID("CC");
        personalInfo.setSecondTypeID(new City());
        personalInfo.setAddress("Casa 135");
        personalInfo.setCity(new City());
        personalInfo.setState("08");
        personalInfo.setOccupation("Otro");
        return personalInfo;
    }

    public static RequestJsonMdw buildRequestJsonWithoutDataInTheBody() {
        RequestJsonMdw requestMdw = new RequestJsonMdw();
        RequestHeaderOut requestHeaderOut = new RequestHeaderOut();
        requestHeaderOut.setHeader(buildHeader());
        Body body = new Body();
        body.setAny(new Object());
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    public static RequestJsonMdw buildRequestWithDataInTheBody() {
        RequestJsonMdw requestMdw = new RequestJsonMdw();
        RequestHeaderOut requestHeaderOut = new RequestHeaderOut();
        requestHeaderOut.setHeader(buildHeader());
        Body body = new Body();
        body.setAny(buildCustomerJsonMdw());
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    public static CustomerJsonMdw buildCustomerJsonMdw() {
        CustomerJsonMdw customerJsonMdw = new CustomerJsonMdw();
        LiteRegistryBrokerRQ liteRegistryBrokerRQ = new LiteRegistryBrokerRQ();
        liteRegistryBrokerRQ.setDeviceInfo(buildDeviceInfo());
        liteRegistryBrokerRQ.setPersonalInfo(buildPersonalInfo());
        liteRegistryBrokerRQ.setDemographicInfo(buildDemographicInfo());
        customerJsonMdw.setLiteRegistryBrokerRQ(liteRegistryBrokerRQ);
        return customerJsonMdw;
    }


}
