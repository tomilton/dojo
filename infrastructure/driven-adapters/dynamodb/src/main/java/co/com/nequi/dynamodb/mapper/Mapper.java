package co.com.nequi.dynamodb.mapper;

import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.model.template.Template;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;

public class Mapper {

    private Mapper() {
    }

    public static TemplateEntity toEntity(Template template) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(template, TemplateEntity.class);
    }

    public static co.com.nequi.model.template.Template toData(TemplateEntity templateEntity) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(templateEntity, Template.class);
    }


}
