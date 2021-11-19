package co.com.nequi.nequiservice.oracle;

import co.com.nequi.model.oracle.DefaultCache;
import co.com.nequi.model.oracle.OracleService;
import co.com.nequi.redis.template.ReactiveRedisTemplateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OracleServiceImpl implements OracleService {
    @Autowired
    private ReactiveRedisTemplateAdapter redisTemplate;

    @Override
    public Mono<DefaultCache> getDefaultProperties() {
        System.out.println("consultando propiedades en cache "+redisTemplate.toString());
        redisTemplate.findById("1").subscribe((e) -> System.out.println("response "+e.toString()));
        return redisTemplate.findById("1").map((res) -> {
            System.out.println("iniciando el map ");
            return res;
        }).flatMap((res) -> {
                    System.out.println("este es el flamap");
                    return Mono.just(new DefaultCache("xxx","xxxx"));
                }).switchIfEmpty(Mono.error(new Exception()))
                .onErrorMap(throwable -> {
                    System.out.println("no se encontraron registros");
                    this.saveDefaultProperties();
                    return new Exception();
                });
    }

    @Override
    public Mono<DefaultCache> saveDefaultProperties() {
        System.out.println("almacenando propiedades en cache");
        return redisTemplate.save("1",new DefaultCache("1","5652222"),1000).onErrorResume(throwable -> Mono.just(new DefaultCache("xxxx","xxxxxx"))).switchIfEmpty(Mono.error(new Exception())).onErrorMap(e -> {
            System.out.println("error al almacenar "+e);
            return e;
        });
    }
}
