package co.com.nequi.model.requestfinacle.customer;

import lombok.Builder;

@lombok.Data
@Builder(toBuilder = true)
public class RelationshipmanagerInfo {
    String relationshipManagerID;
    String relationshipManagerName;
    String primary;
    String department;
    String rowStatus;
}
