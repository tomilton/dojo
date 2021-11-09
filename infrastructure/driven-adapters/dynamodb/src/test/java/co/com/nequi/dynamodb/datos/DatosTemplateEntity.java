package co.com.nequi.dynamodb.datos;

import co.com.nequi.dynamodb.entity.TemplateEntity;

public class DatosTemplateEntity {

    private DatosTemplateEntity() {
    }

    public static TemplateEntity buildTemplateEntity() {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setTemplateID("1");
        return templateEntity;
    }


}
