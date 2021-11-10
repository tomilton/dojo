package co.com.nequi.webclient.json.customer.request;

import com.fasterxml.jackson.annotation.*;

public class RelationshipmanagerInfo {
    private String relationshipManagerID;
    private String relationshipManagerName;
    private String primary;
    private String department;
    private String rowStatus;

    @JsonProperty("relationshipManagerId")
    public String getRelationshipManagerID() {
        return relationshipManagerID;
    }

    @JsonProperty("relationshipManagerId")
    public void setRelationshipManagerID(String value) {
        this.relationshipManagerID = value;
    }

    @JsonProperty("relationshipManagerName")
    public String getRelationshipManagerName() {
        return relationshipManagerName;
    }

    @JsonProperty("relationshipManagerName")
    public void setRelationshipManagerName(String value) {
        this.relationshipManagerName = value;
    }

    @JsonProperty("primary")
    public String getPrimary() {
        return primary;
    }

    @JsonProperty("primary")
    public void setPrimary(String value) {
        this.primary = value;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String value) {
        this.department = value;
    }

    @JsonProperty("__row_status")
    public String getRowStatus() {
        return rowStatus;
    }
    
    @JsonSetter("__row_status")
    public void setRowStatus(String value) {
        this.rowStatus = value;
    }
}
