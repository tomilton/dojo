package co.com.nequi.usecase.createcustomer.helper;

import reactor.core.publisher.Mono;

/**
 * S -> Response
 * Q -> Request
 *
 * @param <S>
 * @param <Q>
 */
public abstract class AbstractUseCase<S, Q> {

    protected abstract Mono<S> execute(Q request);

}
