package co.com.nequi.nequiservice;

import co.com.nequi.model.person.Person;
import co.com.nequi.model.person.gateways.PersonService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ApiService implements PersonService {

    @Override
    public Mono<Person> getPerson(String id) {
        return Mono.just(Person.builder().id(id).balance(100f).build());
    }

    @Override
    public Mono<Float> getBalance(String id) {
        return Mono.just(100f);
    }
}
