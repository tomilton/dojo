package co.com.nequi.trace.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PropertiesConfig getPropertiesConfig(){
        return new PropertiesConfig();
    }
}
