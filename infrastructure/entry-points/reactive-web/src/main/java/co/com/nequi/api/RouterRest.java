package co.com.nequi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler,AccountHandler accountHandler) {
        return route(GET("/api/usecase/getPerson/{id}"), handler::getPerson)
                .andRoute(GET("/api/usecase/getTemplate/{id}"), handler::getTemplate)
                .andRoute(GET("/api/usecase/getAllTemplates"), handler::getAllTemplates)
                .andRoute(POST("/api/customer/createCustomer"), handler::createCustomer)
                .andRoute(POST("/api/customer/getCustomerDetails"), handler::getCustomerDetails)
                .andRoute(POST("/api/account/freezeAccount"), accountHandler::freezeAccount);
    }
}
