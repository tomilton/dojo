package co.com.nequi.dynamodb.mapper;

import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.model.template.TemplateOrigRS;
import co.com.nequi.model.template.TemplateDestRQ;
import co.com.nequi.model.template.TemplateOrigRQ;
import co.com.nequi.model.template.Template;
import co.com.nequi.model.template.TemplateDestRS;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;

public class Mapper {

    private Mapper() {
    }

    public static TemplateEntity toEntity(Template template) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(template, TemplateEntity.class);
    }

    public static Template toData(TemplateEntity templateEntity) {
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(templateEntity, Template.class);
    }

    public static TemplateEntity toEntityMap(Template template) {
        TemplateEntity templateMap = new TemplateEntity();
        templateMap.setTemplateID(template.getTemplateID());
        templateMap.setHost(template.getHost());
        templateMap.setHttpVerb(template.getHttpVerb());
        templateMap.setPort(template.getPort());
        templateMap.setVersion(template.getVersion());
        templateMap.setProtocol(template.getProtocol());
        templateMap.setPath(template.getPath());
        templateMap.setTemplateDestRS(mapTemplateDestRS(template.getTemplateDestRS()));
        templateMap.setTemplateOrigRS(mapTemplateOrigRs(template.getTemplateOrigRS()));
        templateMap.setTemplateOrigRQ(mapTemplateOrigRQ(template.getTemplateOrigRQ()));
        templateMap.setTemplateDestRQ(mapTemplateDestRQ(template.getTemplateDestRQ()));
        //templateMap.setDefaultDataKeys();
        //templateMap.setPropertyRQ();
        //templateMap.setPropertyRS();
        return templateMap;
    }

    private static co.com.nequi.dynamodb.entity.TemplateDestRS mapTemplateDestRS(TemplateDestRS templateDestRS){
        co.com.nequi.dynamodb.entity.TemplateDestRS templateDestRsMap = new co.com.nequi.dynamodb.entity.TemplateDestRS();
        templateDestRsMap.setBody(templateDestRS.getBody());
        return templateDestRsMap;
    }

    private static co.com.nequi.dynamodb.entity.TemplateOrigRS mapTemplateOrigRs(TemplateOrigRS templateOrigRS){
        co.com.nequi.dynamodb.entity.TemplateOrigRS templateOrigRsMap = new co.com.nequi.dynamodb.entity.TemplateOrigRS();
        templateOrigRsMap.setBody(templateOrigRS.getBody());
        return templateOrigRsMap;
    }

    private static co.com.nequi.dynamodb.entity.TemplateOrigRQ mapTemplateOrigRQ(TemplateOrigRQ templateOrigRQ){
        co.com.nequi.dynamodb.entity.TemplateOrigRQ templateOrigRqMap = new co.com.nequi.dynamodb.entity.TemplateOrigRQ();
        templateOrigRqMap.setBody(templateOrigRQ.getBody());
        return templateOrigRqMap;
    }

    private static co.com.nequi.dynamodb.entity.TemplateDestRQ mapTemplateDestRQ(TemplateDestRQ templateDestRQ){
        co.com.nequi.dynamodb.entity.TemplateDestRQ templateDestRqMap = new co.com.nequi.dynamodb.entity.TemplateDestRQ();
        templateDestRqMap.setBody(templateDestRQ.getBody());
        return templateDestRqMap;
    }

}
