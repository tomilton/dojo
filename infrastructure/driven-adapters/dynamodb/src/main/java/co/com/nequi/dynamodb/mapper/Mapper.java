package co.com.nequi.dynamodb.mapper;

import co.com.nequi.dynamodb.entity.Template;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;

public class Mapper {

    private Mapper() {
    }

    public static Template toEntity(co.com.nequi.model.template.Template template) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(template, Template.class);
    }

    public static co.com.nequi.model.template.Template toData(Template templateEntity) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(templateEntity, co.com.nequi.model.template.Template.class);
    }


}
