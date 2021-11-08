package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class Address {
    private String addressFormat;
    private String addressLabel;
    private String addressType;
    private OffsetDateTime residingSince;
    private String preferred;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String houseNumber;
    private String buildingLevel;
    private String premiseName;
    private String streetNumber;
    private String streetName;
    private String locality;
    private String suburb;
    private String town;
    private String rowStatus;

    @JsonProperty("addressFormat")
    public String getAddressFormat() { return addressFormat; }
    @JsonProperty("addressFormat")
    public void setAddressFormat(String value) { this.addressFormat = value; }

    @JsonProperty("addressLabel")
    public String getAddressLabel() { return addressLabel; }
    @JsonProperty("addressLabel")
    public void setAddressLabel(String value) { this.addressLabel = value; }

    @JsonProperty("addressType")
    public String getAddressType() { return addressType; }
    @JsonProperty("addressType")
    public void setAddressType(String value) { this.addressType = value; }

    @JsonProperty("residingSince")
    public OffsetDateTime getResidingSince() { return residingSince; }
    @JsonProperty("residingSince")
    public void setResidingSince(OffsetDateTime value) { this.residingSince = value; }

    @JsonProperty("preferred")
    public String getPreferred() { return preferred; }
    @JsonProperty("preferred")
    public void setPreferred(String value) { this.preferred = value; }

    @JsonProperty("line1")
    public String getLine1() { return line1; }
    @JsonProperty("line1")
    public void setLine1(String value) { this.line1 = value; }

    @JsonProperty("line2")
    public String getLine2() { return line2; }
    @JsonProperty("line2")
    public void setLine2(String value) { this.line2 = value; }

    @JsonProperty("line3")
    public String getLine3() { return line3; }
    @JsonProperty("line3")
    public void setLine3(String value) { this.line3 = value; }

    @JsonProperty("city")
    public String getCity() { return city; }
    @JsonProperty("city")
    public void setCity(String value) { this.city = value; }

    @JsonProperty("state")
    public String getState() { return state; }
    @JsonProperty("state")
    public void setState(String value) { this.state = value; }

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("postalCode")
    public String getPostalCode() { return postalCode; }
    @JsonProperty("postalCode")
    public void setPostalCode(String value) { this.postalCode = value; }

    @JsonProperty("houseNumber")
    public String getHouseNumber() { return houseNumber; }
    @JsonProperty("houseNumber")
    public void setHouseNumber(String value) { this.houseNumber = value; }

    @JsonProperty("buildingLevel")
    public String getBuildingLevel() { return buildingLevel; }
    @JsonProperty("buildingLevel")
    public void setBuildingLevel(String value) { this.buildingLevel = value; }

    @JsonProperty("premiseName")
    public String getPremiseName() { return premiseName; }
    @JsonProperty("premiseName")
    public void setPremiseName(String value) { this.premiseName = value; }

    @JsonProperty("streetNumber")
    public String getStreetNumber() { return streetNumber; }
    @JsonProperty("streetNumber")
    public void setStreetNumber(String value) { this.streetNumber = value; }

    @JsonProperty("streetName")
    public String getStreetName() { return streetName; }
    @JsonProperty("streetName")
    public void setStreetName(String value) { this.streetName = value; }

    @JsonProperty("locality")
    public String getLocality() { return locality; }
    @JsonProperty("locality")
    public void setLocality(String value) { this.locality = value; }

    @JsonProperty("suburb")
    public String getSuburb() { return suburb; }
    @JsonProperty("suburb")
    public void setSuburb(String value) { this.suburb = value; }

    @JsonProperty("town")
    public String getTown() { return town; }
    @JsonProperty("town")
    public void setTown(String value) { this.town = value; }

    @JsonProperty("__row_status")
    public String getRowStatus() { return rowStatus; }
    @JsonProperty("__row_status")
    public void setRowStatus(String value) { this.rowStatus = value; }
}
