package co.com.nequi.dynamodb.controller;


import co.com.nequi.dynamodb.service.TemplateService;
import co.com.nequi.model.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping("/save")
    public Mono<Template> save(@RequestBody Template template) {
        return templateService.save(template);
    }

}
