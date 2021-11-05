package co.com.nequi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class AccountRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(AccountHandler handler) {
        return route(POST("/api/account/freezeAccount"), handler::freezeAccount);
    }
}
