package co.com.nequi.dynamodb.datos;

import co.com.nequi.model.template.Template;

public class DatosTemplate {

    private DatosTemplate() {
    }

    public static Template buildTemplate() {
        Template template = new Template();
        template.setTemplateID("1");
        template.setHttpVerb("GET");
        return template;
    }


}
