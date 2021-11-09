package co.com.nequi.dynamodb.controller;


import co.com.nequi.dynamodb.service.TemplateService;
import co.com.nequi.model.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @GetMapping("/getTemplate/{id}")
    public Mono<Template> getTemplate(@PathVariable() String id) {
        return templateService.getById(id);
    }

    @GetMapping("/all")
    public Flux<Template> getAll() {
        return templateService.getAll();
    }

}
