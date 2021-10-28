package co.com.nequi.dynamodb.mapper;

import co.com.nequi.dynamodb.entity.Template;
import org.springframework.beans.BeanUtils;

public class Mapper {


    public static Template toEntity(co.com.nequi.model.template.Template template) {
        Template templateEntity = new Template();
        BeanUtils.copyProperties(template, templateEntity);
        return templateEntity;
    }

    public static co.com.nequi.model.template.Template toData(Template templateEntity) {
        co.com.nequi.model.template.Template model = new co.com.nequi.model.template.Template();
        BeanUtils.copyProperties(templateEntity, model);
        return model;
    }


}
