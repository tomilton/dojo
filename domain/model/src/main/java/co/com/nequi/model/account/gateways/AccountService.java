package co.com.nequi.model.account.gateways;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<FreezeAccountRsService> freezeAccount(FreezeAccountRQ freezeAccountRQ);
}
