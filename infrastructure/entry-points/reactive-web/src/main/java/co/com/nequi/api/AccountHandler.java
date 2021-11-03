package co.com.nequi.api;

import co.com.nequi.model.account.ResponseAccount;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.person.Person;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {
    private  FreezeAccountUseCase freezeUserCase;

    public Mono<ServerResponse> freezeAccount(ServerRequest serverRequest){
        //TODO se debe cambiar el objeto a el request completo
        Mono<FreezeAccountRqDto> freezeAccountRqDtoMono = serverRequest.bodyToMono(FreezeAccountRqDto.class);
        return ServerResponse.ok().body(freezeUserCase.freezeAccount(), ResponseAccount.class);
    }
}
