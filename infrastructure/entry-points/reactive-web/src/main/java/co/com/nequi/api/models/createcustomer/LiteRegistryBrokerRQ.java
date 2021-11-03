package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class LiteRegistryBrokerRQ {
    private DeviceInfo deviceInfo;
    private PersonalInfo personalInfo;
    private DemographicInfo demographicInfo;

    @JsonProperty("deviceInfo")
    public DeviceInfo getDeviceInfo() { return deviceInfo; }
    @JsonProperty("deviceInfo")
    public void setDeviceInfo(DeviceInfo value) { this.deviceInfo = value; }

    @JsonProperty("personalInfo")
    public PersonalInfo getPersonalInfo() { return personalInfo; }
    @JsonProperty("personalInfo")
    public void setPersonalInfo(PersonalInfo value) { this.personalInfo = value; }

    @JsonProperty("demographicInfo")
    public DemographicInfo getDemographicInfo() { return demographicInfo; }
    @JsonProperty("demographicInfo")
    public void setDemographicInfo(DemographicInfo value) { this.demographicInfo = value; }
}
