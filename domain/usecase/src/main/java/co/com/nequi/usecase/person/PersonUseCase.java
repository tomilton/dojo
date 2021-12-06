package co.com.nequi.usecase.person;

import co.com.nequi.model.person.Person;
import co.com.nequi.model.person.gateways.PersonService;
import co.com.nequi.model.template.Template;
import co.com.nequi.model.template.gateways.TemplateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private final PersonService serviceGateway;

    private final TemplateRepository templateRepository;

    public Mono<Person> getPerson(String id) {
        return serviceGateway.getPerson(id);
    }

    public Mono<Float> getBalance(String id) {
        return serviceGateway.getBalance(id);
    }

    public Mono<Template> getTemplateById(String idTemplate) {
        return this.templateRepository.getById(idTemplate).log();
    }

    public Flux<Template> getAllTemplates() {
        return this.templateRepository.getAll();
    }


    public Mono<Template> saveTemplate(Template template) {
        return this.templateRepository.save(template);
    }


}
