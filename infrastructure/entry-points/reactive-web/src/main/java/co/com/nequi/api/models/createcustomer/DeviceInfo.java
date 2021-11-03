package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class DeviceInfo {
    private String phoneNumber;
    private String deviceID;
    private String password;

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    @JsonProperty("deviceId")
    public String getDeviceID() { return deviceID; }
    @JsonProperty("deviceId")
    public void setDeviceID(String value) { this.deviceID = value; }

    @JsonProperty("password")
    public String getPassword() { return password; }
    @JsonProperty("password")
    public void setPassword(String value) { this.password = value; }
}
