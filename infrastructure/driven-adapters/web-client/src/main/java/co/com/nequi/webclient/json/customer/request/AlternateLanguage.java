package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class AlternateLanguage {
    private String attributeName;
    private String languageCode;
    private String description;

    @JsonProperty("attributeName")
    public String getAttributeName() { return attributeName; }
    @JsonProperty("attributeName")
    public void setAttributeName(String value) { this.attributeName = value; }

    @JsonProperty("languageCode")
    public String getLanguageCode() { return languageCode; }
    @JsonProperty("languageCode")
    public void setLanguageCode(String value) { this.languageCode = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }
}
