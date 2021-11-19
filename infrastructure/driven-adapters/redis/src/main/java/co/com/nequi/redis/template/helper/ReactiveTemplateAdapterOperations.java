package co.com.nequi.redis.template.helper;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.util.function.Function;

public abstract class ReactiveTemplateAdapterOperations<E, K, V> {
    private final ReactiveRedisTemplate<K, V> template;
    private final Class<V> dataClass;
    protected ObjectMapper mapper;
    private final Function<V, E> toEntityFn;

    @SuppressWarnings("unchecked")
    public ReactiveTemplateAdapterOperations(ReactiveRedisConnectionFactory connectionFactory, ObjectMapper mapper, Function<V, E> toEntityFn) {
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<V>) genericSuperclass.getActualTypeArguments()[2];
        this.toEntityFn = toEntityFn;

        RedisSerializationContext<K, V> serializationContext =
                RedisSerializationContext.<K, V>newSerializationContext(new Jackson2JsonRedisSerializer<>(dataClass))
                        .build();

        template = new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
        System.out.println("constructor adaptador redis ok connectionFactory "+connectionFactory.toString());
        System.out.println("constructor adaptador redis ok a "+mapper.toString());
        System.out.println("constructor adaptador redis ok b "+dataClass.toString());
    }

    public Mono<E> save(K key, E entity) {
       Mono.just(entity)
                .map(this::toValue)
                .flatMap(value -> {
                    System.out.println("almacenando cache k "+key);
                    System.out.println("almacenando cache value "+value);
                    return template.opsForValue().set(key, value);
                }).onErrorMap((e) -> {
                    System.out.println("error almacenando en cache "+e.getMessage());
                    return e;
                }).onErrorResume(throwable -> {
                   System.out.println("error resume "+throwable);
                   return Mono.empty();
               } ).onErrorContinue((throwable, o) -> {
                   System.out.println("este es el objeto de error "+ o.toString());
               }).subscribe(response -> System.out.println("response "+response));
        return Mono.empty();
    }

    public Mono<E> save(K key, E entity, long expirationMillis) {
        return save(key, entity)
                .flatMap(v -> template.expire(key, Duration.ofMillis(expirationMillis)).thenReturn(v));
    }

    public Mono<E> findById(K key) {
        return template.opsForValue().get(key)
                .map(this::toEntity);
    }

    protected V toValue(E entity) {
        return mapper.map(entity, dataClass);
    }

    protected E toEntity(V data) {
        return data != null ? toEntityFn.apply(data) : null;
    }

}
