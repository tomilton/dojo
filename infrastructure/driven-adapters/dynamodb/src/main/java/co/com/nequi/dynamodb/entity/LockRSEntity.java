package co.com.nequi.dynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class LockRSEntity {

    private String custId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String name;
    private String titlePrefix;
    private String freezeStatusCode;
    private String freezeReasonCode;
    @DynamoDbPartitionKey
    @DynamoDbAttribute("custId")
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
    @DynamoDbAttribute("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @DynamoDbAttribute("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @DynamoDbAttribute("middleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DynamoDbAttribute("titlePrefix")
    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }
    @DynamoDbAttribute("freezeStatusCode")
    public String getFreezeStatusCode() {
        return freezeStatusCode;
    }

    public void setFreezeStatusCode(String freezeStatusCode) {
        this.freezeStatusCode = freezeStatusCode;
    }
    @DynamoDbAttribute("freezeReasonCode")
    public String getFreezeReasonCode() {
        return freezeReasonCode;
    }

    public void setFreezeReasonCode(String freezeReasonCode) {
        this.freezeReasonCode = freezeReasonCode;
    }


    @Override
    public String toString() {
        return "getLockRS{" +
                "custId='" + custId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", name='" + name + '\'' +
                ", titlePrefix='" + titlePrefix + '\'' +
                ", freezeStatusCode='" + freezeStatusCode + '\'' +
                ", freezeReasonCode='" + freezeReasonCode + '\'' +
                '}';
    }
}
