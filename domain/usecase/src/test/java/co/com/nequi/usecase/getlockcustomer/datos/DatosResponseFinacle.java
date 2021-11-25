package co.com.nequi.usecase.getlockcustomer.datos;

import co.com.nequi.model.responsefinacle.customer.*;
import co.com.nequi.model.responsefinacle.detailprueba.Meta;
import co.com.nequi.model.responsefinacle.detailprueba.DataLock;
import co.com.nequi.model.template.LockRS;

public class DatosResponseFinacle {
public static CustomerResponseFinacle2 responseFinacleSuccess() {
    Meta meta = new Meta();
    meta.setRequestuuid("string");
    meta.setGlobaluuid("string");
    meta.setContexturl("string");
    CustomerResponseFinacle2 customerResponseFinacle2 = new CustomerResponseFinacle2();
    DataLock dataLock  = new DataLock();
    SavingAccountInquiryRs savingAccountInquiryRs = new SavingAccountInquiryRs();
    savingAccountInquiryRs.setCustomerId("1225");
    savingAccountInquiryRs.setProductCode("1000");
    savingAccountInquiryRs.setAccountCurrency("Nomina");
    savingAccountInquiryRs.setAccountName("Cuenta de nomina");
    savingAccountInquiryRs.setAccountShortName("cNomina");
    savingAccountInquiryRs.setAccountOpenDate("19/11/2018");
    savingAccountInquiryRs.setAccountFreezeStatus("ACTIVA");
    savingAccountInquiryRs.setAccountStatus("ACTIVA");
    savingAccountInquiryRs.setAccountAPY(1);
    savingAccountInquiryRs.setAccountCloseFlag("string");
    savingAccountInquiryRs.setInterestCreditAccountIndicator("19%");
    savingAccountInquiryRs.setInterestCreditAccountId("1225");
    savingAccountInquiryRs.setEffectiveAvailableBalance(1);
    savingAccountInquiryRs.setAvailableBalance(1);
    savingAccountInquiryRs.setClearBalance(1);
    dataLock.setSavingAccountInquiryRs(savingAccountInquiryRs);
    customerResponseFinacle2.setMeta(meta);
    customerResponseFinacle2.setData(dataLock);
    return customerResponseFinacle2;
}

    public static CustomerResponseFinacle2 responseFinacleError() {
        CustomerResponseFinacle2 customerResponseFinacle2 = new CustomerResponseFinacle2();
        Meta meta = new Meta();
        meta.setRequestuuid("string");
        meta.setGlobaluuid("string");
        meta.setContexturl("string");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorcode("422");
        errorDetail.setErrordesc("error 422");
        customerResponseFinacle2.setMeta(meta);
        customerResponseFinacle2.setErrorDetail(errorDetail);
        return customerResponseFinacle2;
    }

    public static LockRS responserLockRS(){
        LockRS lockRS = new LockRS();
        lockRS.setCustId("1225");
        lockRS.setFirstName("Ana");
        lockRS.setMiddleName("jimenez");
        lockRS.setName("anaxd");
        lockRS.setTitlePrefix("Ahorrros");
        lockRS.setFreezeStatusCode("C");
        lockRS.setFreezeReasonCode("D");
        return lockRS;
    }
    public static LockRS responserLockRS1(){
        LockRS lockRS = new LockRS();
        lockRS.setCustId("1225");
        lockRS.setFirstName("Ana");
        lockRS.setMiddleName("jimenez");
        lockRS.setName("anaxd");
        lockRS.setTitlePrefix("Ahorrrosss");
        lockRS.setFreezeStatusCode("C");
        lockRS.setFreezeReasonCode("D");
        return lockRS;
    }
}
