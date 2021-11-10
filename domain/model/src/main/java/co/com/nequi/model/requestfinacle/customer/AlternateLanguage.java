package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class AlternateLanguage {
    private String attributeName;
    private String languageCode;
    private String description;
}
