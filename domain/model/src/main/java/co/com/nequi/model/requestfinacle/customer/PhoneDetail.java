package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class PhoneDetail {
    private String countryCode;
    private String phoneType;
    private String phoneNumber;
    private String preferred;
    private String rowStatus;
}
