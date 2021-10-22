package co.com.nequi.model.person.gateways;

import co.com.nequi.model.person.Person;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<Person> getPerson(String id);

    Mono<Float> getBalance(String id);

}
