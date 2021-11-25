package co.com.nequi.usecase.getlockcustomer;

import co.com.nequi.model.customer.GetLockRQ;
import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle2;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.model.template.Lock;
import co.com.nequi.model.template.LockRS;
import co.com.nequi.model.template.gateways.LockRepository;
import co.com.nequi.usecase.getlockcustomer.helper.AbstractGetLockUseCase;
import co.com.nequi.usecase.util.ResponseUtil;
import co.com.nequi.usecase.util.UtilString;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class GetLockCustomerUseCase extends AbstractGetLockUseCase<ResponseMdw, RequestMdw> {
    private final CustomerServiceFinacle customerServiceFinacle;

    private final LockRepository lockRepository;

    public Mono<ResponseMdw> getLockCustomer(RequestMdw requestMdw) {
        String bankId = "1";
        String customerId = "12";
        GetLockRQ lockRQ = (GetLockRQ) requestMdw.getRequestHeaderOut().getBody().getAny();
        Mono<CustomerResponseFinacle2> getLockFinacle = customerServiceFinacle.getLock(bankId, lockRQ.getLockRQ().getIdAccount());
        return getLockFinacle.map(getLockFinacleResponse -> {
                    LockRS getLockRepository = lockRepository.getById(customerId);
                    return getResponseLock(getLockFinacleResponse, getLockRepository);
                }).map((resp -> ResponseUtil.buildResponseSuccess(resp, requestMdw)))
                .onErrorResume(error -> ResponseUtil.handleErrors(error, requestMdw));
    }


    public Lock getResponseLock(CustomerResponseFinacle2 getCustomerServiceFinacle, LockRS getLockRepository) {
        Lock lock = new Lock();
        LockRS lockRS = new LockRS();
        lockRS.setCustId(getCUstomerId(getCustomerServiceFinacle));
        lockRS.setLastName(getLastName(getLockRepository));
        lockRS.setFirstName(getFirstName(getLockRepository));
        lockRS.setMiddleName(getMiddleName(getLockRepository));
        lockRS.setName(getName(getLockRepository));
        lockRS.setTitlePrefix(getTitlePrefix(getLockRepository));
        lockRS.setFreezeStatusCode(getAccountFreezeStatus(getCustomerServiceFinacle));
        lockRS.setFreezeReasonCode(getFreezeReasonCode(getLockRepository));
        lock.setGetLockRS(lockRS);
        return lock;
    }

    private String getCUstomerId(CustomerResponseFinacle2 getCustomerServiceFinacle) {
        String customerId = getCustomerServiceFinacle.getData().getSavingAccountInquiryRs().getCustomerId();
        if (!UtilString.cadenaVacia(customerId)) {
            return customerId;
        } else {
            return "";
        }
    }

    private String getLastName(LockRS getLockRepository) {
        String lastName = getLockRepository.getLastName();
        if (!UtilString.cadenaVacia(lastName)) {
            return lastName;
        } else {
            return "";
        }
    }


    private String getFirstName(LockRS getLockRepository) {
        String firstName = getLockRepository.getFirstName();
        if (!UtilString.cadenaVacia(firstName)) {
            return firstName;
        } else {
            return "";
        }
    }

    private String getMiddleName(LockRS getLockRepository) {
        String middleName = getLockRepository.getMiddleName();
        if (!UtilString.cadenaVacia(middleName)) {
            return middleName;
        } else {
            return "";
        }
    }

    private String getName(LockRS getLockRepository) {
        String name = getLockRepository.getName();
        if (!UtilString.cadenaVacia(name)) {
            return name;
        } else {
            return "";
        }
    }

    private String getTitlePrefix(LockRS getLockRepository) {
        String titlePrefix = getLockRepository.getTitlePrefix();
        if (!UtilString.cadenaVacia(titlePrefix)) {
            return titlePrefix;
        } else {
            return "";
        }
    }

    private String getAccountFreezeStatus(CustomerResponseFinacle2 getCustomerServiceFinacle) {
        String accountFreezeStatus = getCustomerServiceFinacle.getData().getSavingAccountInquiryRs().getAccountFreezeStatus();
        if (!UtilString.cadenaVacia(accountFreezeStatus)) {
            return accountFreezeStatus;
        } else {
            return "";
        }
    }

    private String getFreezeReasonCode(LockRS getLockRepository) {
        String freezeReasonCode = getLockRepository.getFreezeReasonCode();
        if (!UtilString.cadenaVacia(freezeReasonCode)) {
            return freezeReasonCode;
        } else {
            return "";
        }
    }
}
