package co.com.nequi.model.account.gateways;

import co.com.nequi.model.account.ResponseAccount;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<ResponseAccount> freezeAccount(FreezeAccountRqDto freezeAccountRqDto);
}
