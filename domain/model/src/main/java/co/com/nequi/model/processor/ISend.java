package co.com.nequi.model.processor;

public interface ISend<T,R> {
    R execute(T t);
}
