package co.com.nequi.nequiservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurationService {
    @Bean
    public PropertiesConfigService getPropertiesConfigService(){
        return new PropertiesConfigService();
    }
}
