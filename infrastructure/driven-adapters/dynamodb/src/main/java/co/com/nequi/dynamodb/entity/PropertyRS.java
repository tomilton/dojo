package co.com.nequi.dynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class PropertyRS {

    private String propertyOrig;
    private String propertyDest;
    private String integrationType;
    private String valueConstant;
    private String integrationLogic;


    public String getPropertyOrig() {
        return propertyOrig;
    }

    public void setPropertyOrig(String propertyOrig) {
        this.propertyOrig = propertyOrig;
    }

    public String getPropertyDest() {
        return propertyDest;
    }

    public void setPropertyDest(String propertyDest) {
        this.propertyDest = propertyDest;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public String getValueConstant() {
        return valueConstant;
    }

    public void setValueConstant(String valueConstant) {
        this.valueConstant = valueConstant;
    }

    public String getIntegrationLogic() {
        return integrationLogic;
    }

    public void setIntegrationLogic(String integrationLogic) {
        this.integrationLogic = integrationLogic;
    }

    @Override
    public String toString() {
        return "PropertyRS{" +
                "propertyOrig='" + propertyOrig + '\'' +
                ", propertyDest='" + propertyDest + '\'' +
                ", integrationType='" + integrationType + '\'' +
                ", valueConstant='" + valueConstant + '\'' +
                ", integrationLogic='" + integrationLogic + '\'' +
                '}';
    }
}
