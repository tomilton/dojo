package co.com.nequi.config;

import co.com.nequi.model.person.gateways.PersonService;
import co.com.nequi.usecase.person.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.nequi.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    /*@Bean
    public PersonUseCase getPersonUseCase(PersonService serviceGateway) {
        return new PersonUseCase(serviceGateway);
    }*/

}
