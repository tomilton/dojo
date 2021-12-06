package co.com.nequi.model.processor;

import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.ResponseMdw;
import reactor.core.publisher.Mono;

public interface IprocessAdapter {
    Mono<ResponseMdw> execute(RequestMdw request);
}
