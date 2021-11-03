package co.com.nequi.dynamodb.mapper;

import org.reactivecommons.utils.ObjectMapper;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class MapperAbstract<E, C> {

    protected ObjectMapper mapper;
    private Class<C> dataClass;
    private Function<C, E> toEntityFn;

    public MapperAbstract(ObjectMapper mapper) {
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<C>) genericSuperclass.getActualTypeArguments()[1];
    }

    protected C toData(E entity) {
        return mapper.map(entity, dataClass);
    }

    protected E toEntity(C data) {
        return data != null ? toEntityFn.apply(data) : null;
    }

}
