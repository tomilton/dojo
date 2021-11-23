package co.com.nequi.model.account.gateways;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.FreezeAccountRsService;
import co.com.nequi.model.account.dto.UnFreezeAccountRq;
import co.com.nequi.model.account.dto.UnFreezeAccountRsService;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<FreezeAccountRsService> freezeAccount(FreezeAccountRQ freezeAccountRQ,String bankId);
    Mono<UnFreezeAccountRsService> unFreezeAccount(UnFreezeAccountRq unFreezeAccountRq);
}
