package co.com.nequi.model.customer;

public class LockRS {
    private String custId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String name;
    private String titlePrefix;
    private String freezeStatusCode;
    private String freezeReasonCode;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getFreezeStatusCode() {
        return freezeStatusCode;
    }

    public void setFreezeStatusCode(String freezeStatusCode) {
        this.freezeStatusCode = freezeStatusCode;
    }

    public String getFreezeReasonCode() {
        return freezeReasonCode;
    }

    public void setFreezeReasonCode(String freezeReasonCode) {
        this.freezeReasonCode = freezeReasonCode;
    }

}
