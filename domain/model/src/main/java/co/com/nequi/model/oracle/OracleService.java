package co.com.nequi.model.oracle;

import reactor.core.publisher.Mono;

public interface OracleService {
    Mono<DefaultCache> getDefaultProperties();
    Mono<DefaultCache> saveDefaultProperties();
}
