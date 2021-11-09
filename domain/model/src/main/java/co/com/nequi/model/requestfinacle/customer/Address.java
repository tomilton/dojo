package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class Address {
    private String addressFormat;
    private String addressLabel;
    private String addressType;
    private String residingSince;
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
}
