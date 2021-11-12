package co.com.nequi.nequiservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class PropertiesConfigService {
    @Value("${finacle.uri.freezeAccount}")
    private String uriFreezeAccount;

    @Value("${finacle.uri.unfreezeAccount}")
    private String uriUnFreezeAccount;
}
