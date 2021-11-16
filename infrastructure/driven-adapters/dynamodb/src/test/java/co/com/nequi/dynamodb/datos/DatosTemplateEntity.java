package co.com.nequi.dynamodb.datos;

import co.com.nequi.dynamodb.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DatosTemplateEntity {

    private DatosTemplateEntity() {
    }

    public static TemplateEntity buildTemplateEntity() {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setTemplateID("1");
        templateEntity.setTemplateOrigRQ(buildTemplateOrigRQ());
        templateEntity.setTemplateDestRQ(buildTemplateDestRQ());
        templateEntity.setTemplateOrigRS(buildTemplateOrigRS());
        templateEntity.setTemplateDestRS(buildTemplateDestRS());
        templateEntity.setPropertyRS(buildPropertyRS());
        templateEntity.setPropertyRQ(buildPropertyRQ());
        return templateEntity;
    }

    public static TemplateOrigRQ buildTemplateOrigRQ() {
        TemplateOrigRQ templateOrigRQ = new TemplateOrigRQ();
        templateOrigRQ.setBody("templateOrigRQ");
        return templateOrigRQ;
    }

    public static TemplateDestRQ buildTemplateDestRQ() {
        TemplateDestRQ templateDestRQ = new TemplateDestRQ();
        templateDestRQ.setBody("templateDestRQ");
        return templateDestRQ;
    }

    public static TemplateOrigRS buildTemplateOrigRS() {
        TemplateOrigRS templateOrigRS = new TemplateOrigRS();
        templateOrigRS.setBody("templateOrigRS");
        return templateOrigRS;
    }

    public static TemplateDestRS buildTemplateDestRS() {
        TemplateDestRS templateDestRS = new TemplateDestRS();
        templateDestRS.setBody("templateDestRS");
        return templateDestRS;
    }

    public static List<PropertyRS> buildPropertyRS() {
        List<PropertyRS> propertyRSList = new ArrayList<>();
        PropertyRS propertyRS = new PropertyRS();
        propertyRS.setPropertyOrig("accountId");
        propertyRS.setPropertyDest("accountId");
        propertyRS.setIntegrationType("copyPageLogic");
        propertyRS.setValueConstant("");
        propertyRS.setIntegrationLogic("");
        propertyRSList.add(propertyRS);
        return propertyRSList;
    }

    public static List<PropertyRQ> buildPropertyRQ() {
        List<PropertyRQ> propertyRQList = new ArrayList<>();
        PropertyRQ propertyRQ = new PropertyRQ();
        propertyRQ.setPropertyOrig("accountId");
        propertyRQ.setPropertyDest("accountId");
        propertyRQ.setIntegrationType("copyPageLogic");
        propertyRQ.setValueConstant("");
        propertyRQ.setIntegrationLogic("");
        propertyRQList.add(propertyRQ);
        return propertyRQList;
    }
}
