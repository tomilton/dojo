package co.com.nequi.model.template.gateways;

import co.com.nequi.model.template.LockRS;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LockRepository {

    LockRS getById(String id);
}
