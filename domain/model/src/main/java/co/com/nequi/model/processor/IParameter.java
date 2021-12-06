package co.com.nequi.model.processor;

public interface IParameter<T,R> {
    R execute(T t);
}
