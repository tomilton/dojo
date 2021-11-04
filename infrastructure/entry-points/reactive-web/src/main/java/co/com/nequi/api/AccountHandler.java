package co.com.nequi.api;

import co.com.nequi.model.account.ResponseFreezeAccount;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
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
        return ServerResponse.ok().body(freezeUserCase.freezeAccount(), ResponseFreezeAccount.class);
    }
}
