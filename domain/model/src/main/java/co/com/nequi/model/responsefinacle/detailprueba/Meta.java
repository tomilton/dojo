package co.com.nequi.model.responsefinacle.detailprueba;

import java.util.List;

public class Meta {
    private String requestuuid;
    private String globaluuid;
    private String contexturl;
    private List<ErrorDetail> errorDetails;


    public String getRequestuuid() { return requestuuid; }

    public void setRequestuuid(String value) { this.requestuuid = value; }


    public String getGlobaluuid() { return globaluuid; }

    public void setGlobaluuid(String value) { this.globaluuid = value; }


    public String getContexturl() { return contexturl; }

    public void setContexturl(String value) { this.contexturl = value; }


    public List<ErrorDetail> getErrorDetails() { return errorDetails; }

    public void setErrorDetails(List<ErrorDetail> value) { this.errorDetails = value; }
}
