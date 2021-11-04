package co.com.nequi.usecase.freezeaccount;

import co.com.nequi.model.account.ResponseFreezeAccount;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.account.gateways.AccountService;
import co.com.nequi.model.template.Template;
import co.com.nequi.model.template.gateways.TemplateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FreezeAccountUseCase {
    private final AccountService accountService;

    private final TemplateRepository templateRepository;

    public Mono<FreezeAccountResponse> freezeAccount(){
        Mono<Template> template = this.templateRepository.getById("freezeAccount");
        FreezeAccountRqDto freezeAccountRqDto = FreezeAccountRqDto.builder()
                .freezeCode("D")
                .accountNumber("87052427983")
                .reasonCode(10).build();
        Mono<ResponseFreezeAccount> responseFreezeAccount = this.accountService.freezeAccount(freezeAccountRqDto);

        return Mono.zip(template,responseFreezeAccount).map(objects -> {
            Template templateMono = objects.getT1();
            ResponseFreezeAccount responseAccount = objects.getT2();
            return responseAccount;
        });

    }

}
