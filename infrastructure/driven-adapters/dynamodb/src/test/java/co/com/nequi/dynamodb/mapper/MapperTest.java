package co.com.nequi.dynamodb.mapper;

import co.com.nequi.dynamodb.datos.DatosTemplate;
import co.com.nequi.dynamodb.datos.DatosTemplateEntity;
import co.com.nequi.dynamodb.entity.TemplateEntity;
import co.com.nequi.model.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = Mapper.class)
@EnableAutoConfiguration
class MapperTest {

    @Test
    void testToEntity() {
        Template template = DatosTemplate.buildTemplate();
        TemplateEntity mapperEntity = Mapper.toEntity(template);
        assertNotNull(mapperEntity);
    }

    @Test
    void testToTemplate() {
        TemplateEntity templateEntity = DatosTemplateEntity.buildTemplateEntity();
        Template mapperEntity = Mapper.toData(templateEntity);
        assertNotNull(mapperEntity);
    }


}