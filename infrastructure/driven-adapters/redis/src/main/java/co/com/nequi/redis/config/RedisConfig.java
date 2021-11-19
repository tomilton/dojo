package co.com.nequi.redis.config;

import co.com.nequi.redis.template.ReactiveRedisTemplateAdapter;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
//import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
//@EnableElastiCache({@CacheClusterConfig(name = "usercache")})
//@EnableElastiCache
public class RedisConfig {
 /**   @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }**/

    /**@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }**/

   /** @Bean(value = "reactiveRedisTemplateAdapter")
    public ReactiveRedisTemplateAdapter reactiveRedisTemplateAdapter(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory, ObjectMapper objectMapper){
        ReactiveRedisTemplateAdapter redisTemplateAdapter = new ReactiveRedisTemplateAdapter(reactiveRedisConnectionFactory,objectMapper);
        return redisTemplateAdapter;
    }**/

   /** @Bean(name = "cacheManager")
    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .build();
    }**/
}
