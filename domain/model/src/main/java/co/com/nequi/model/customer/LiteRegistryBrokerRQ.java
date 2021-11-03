package co.com.nequi.model.customer;

public class LiteRegistryBrokerRQ {
    private DeviceInfo deviceInfo;
    private PersonalInfo personalInfo;
    private DemographicInfo demographicInfo;

    public DeviceInfo getDeviceInfo() { return deviceInfo; }
    public void setDeviceInfo(DeviceInfo value) { this.deviceInfo = value; }

    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(PersonalInfo value) { this.personalInfo = value; }

    public DemographicInfo getDemographicInfo() { return demographicInfo; }
    public void setDemographicInfo(DemographicInfo value) { this.demographicInfo = value; }
}
