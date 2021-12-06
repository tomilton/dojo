package co.com.nequi.model.template;

import java.util.List;

public class Template {

    private String templateID;
    private TemplateOrigRQ templateOrigRQ;
    private TemplateDestRQ templateDestRQ;

    private TemplateOrigRS templateOrigRS;
    private TemplateDestRS templateDestRS;

    private String httpVerb;
    private String protocol;
    private String host;
    private String port;
    private String path;
    private String region;
    private String version;

    private List<PropertyRS> propertyRS;
    private List<PropertyRQ> propertyRQ;

    private List<DefaultDataKeyProperty> defaultDataKeys;


    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public TemplateOrigRQ getTemplateOrigRQ() {
        return templateOrigRQ;
    }

    public void setTemplateOrigRQ(TemplateOrigRQ templateOrigRQ) {
        this.templateOrigRQ = templateOrigRQ;
    }

    public TemplateDestRQ getTemplateDestRQ() {
        return templateDestRQ;
    }

    public void setTemplateDestRQ(TemplateDestRQ templateDestRQ) {
        this.templateDestRQ = templateDestRQ;
    }

    public TemplateOrigRS getTemplateOrigRS() {
        return templateOrigRS;
    }

    public void setTemplateOrigRS(TemplateOrigRS templateOrigRS) {
        this.templateOrigRS = templateOrigRS;
    }

    public TemplateDestRS getTemplateDestRS() {
        return templateDestRS;
    }

    public void setTemplateDestRS(TemplateDestRS templateDestRS) {
        this.templateDestRS = templateDestRS;
    }

    public String getHttpVerb() {
        return httpVerb;
    }

    public void setHttpVerb(String httpVerb) {
        this.httpVerb = httpVerb;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<PropertyRS> getPropertyRS() {
        return propertyRS;
    }

    public void setPropertyRS(List<PropertyRS> propertyRS) {
        this.propertyRS = propertyRS;
    }

    public List<PropertyRQ> getPropertyRQ() {
        return propertyRQ;
    }

    public void setPropertyRQ(List<PropertyRQ> propertyRQ) {
        this.propertyRQ = propertyRQ;
    }

    public List<DefaultDataKeyProperty> getDefaultDataKeys() {
        return defaultDataKeys;
    }

    public void setDefaultDataKeys(List<DefaultDataKeyProperty> defaultDataKeys) {
        this.defaultDataKeys = defaultDataKeys;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateID='" + templateID + '\'' +
                ", templateOrigRQ=" + templateOrigRQ +
                ", templateDestRQ=" + templateDestRQ +
                ", templateOrigRS=" + templateOrigRS +
                ", templateDestRS=" + templateDestRS +
                ", httpVerb='" + httpVerb + '\'' +
                ", protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", path=" + path +
                ", region='" + region + '\'' +
                ", version='" + version + '\'' +
                ", propertyRS=" + propertyRS +
                ", propertyRQ=" + propertyRQ +
                ", defaultDataKeys=" + defaultDataKeys +
                '}';
    }
}
