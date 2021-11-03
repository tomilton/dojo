package co.com.nequi.model.responsemdw;

public class ResponseStatus {
    private String statusCode;
    private String statusDesc;
    private String errorMessage;
    private String system;

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String value) { this.statusCode = value; }

    public String getStatusDesc() { return statusDesc; }
    public void setStatusDesc(String value) { this.statusDesc = value; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String value) { this.errorMessage = value; }

    public String getSystem() { return system; }
    public void setSystem(String value) { this.system = value; }
}
