package co.com.nequi.usecase.getlockcustomer.helper;

import reactor.core.publisher.Mono;

public abstract class AbstractGetLockUseCase<S, Q> {
    protected abstract Mono<S> getLockCustomer(Q request);
}
