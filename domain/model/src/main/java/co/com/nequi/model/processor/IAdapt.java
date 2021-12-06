package co.com.nequi.model.processor;

public interface IAdapt<T,R> {
    R execute(T t);
}
