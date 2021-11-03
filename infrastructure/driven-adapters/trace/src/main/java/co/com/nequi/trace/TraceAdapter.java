package co.com.nequi.trace;

public interface TraceAdapter<T> {
    void generate(T t);
}
