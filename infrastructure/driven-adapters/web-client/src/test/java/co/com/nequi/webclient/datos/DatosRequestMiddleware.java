package co.com.nequi.webclient.datos;

import co.com.nequi.model.customer.*;

public class DatosRequestMiddleware {

    private DatosRequestMiddleware() {
    }

    public static DeviceInfo buildDeviceInfo() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setPhoneNumber("phoneNumber");
        deviceInfo.setDeviceID("488e6df9b9372927");
        deviceInfo.setPassword("##HIDDEN_DATA##");
        return deviceInfo;
    }

    public static DemographicInfo buildDemographicInfo() {
        DemographicInfo demographicInfo = new DemographicInfo();
        demographicInfo.setGrossIncome("grossIncome");
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

    public static LiteRegistryBrokerRQ buildLiteRegistryBrokerRQ() {
        LiteRegistryBrokerRQ liteRegistryBrokerRQ = new LiteRegistryBrokerRQ();
        liteRegistryBrokerRQ.setDeviceInfo(buildDeviceInfo());
        liteRegistryBrokerRQ.setPersonalInfo(buildPersonalInfo());
        liteRegistryBrokerRQ.setDemographicInfo(buildDemographicInfo());
        return liteRegistryBrokerRQ;
    }

    public static Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setLiteRegistryBrokerRQ(buildLiteRegistryBrokerRQ());
        return customer;
    }


}
