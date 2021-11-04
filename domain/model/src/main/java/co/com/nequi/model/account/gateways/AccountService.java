package co.com.nequi.model.account.gateways;

import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Boolean> freezeAccount(FreezeAccountRqDto freezeAccountRqDto);
}
