package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class RelationshipmanagerInfo {
    private String relationshipManagerID;
    private String relationshipManagerName;
    private String primary;
    private String department;
    private String rowStatus;
}
