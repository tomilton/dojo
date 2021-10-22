package co.com.nequi.usecase.person;

import co.com.nequi.model.person.Person;
import co.com.nequi.model.person.gateways.PersonService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private final PersonService serviceGateway;

    public Mono<Person> getPerson(String id) {
        return serviceGateway.getPerson(id);
    }

    public Mono<Float> getBalance(String id) {
        return serviceGateway.getBalance(id);
    }

}
