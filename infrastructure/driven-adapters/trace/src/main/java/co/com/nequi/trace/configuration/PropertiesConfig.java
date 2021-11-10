package co.com.nequi.trace.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Getter
@Setter
public class PropertiesConfig {
    @Value("${traceconfig.type}")
    private String typeConfig;
}
