package co.com.nequi.webclient.services.datos;


import co.com.nequi.model.customer.gateways.CustomerServiceFinacle;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle2;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON2;
import co.com.nequi.webclient.json.customer.response.DataLock;
import co.com.nequi.webclient.json.customer.response.Meta;
import co.com.nequi.webclient.json.customer.response.SavingAccountInquiryRs;

public class Request {

    public static CustomerResponseJSON2 buildsucessResponse() {
        CustomerResponseJSON2 customerResponseJSON2 = new CustomerResponseJSON2();
        SavingAccountInquiryRs savingAccountInquiryRs = new SavingAccountInquiryRs();
        savingAccountInquiryRs.setCustomerId("id");
        savingAccountInquiryRs.setProductCode("88");
        savingAccountInquiryRs.setAccountCurrency("30");
        savingAccountInquiryRs.setAccountName("Ana Vega");
        savingAccountInquiryRs.setAccountShortName("Ana");
        savingAccountInquiryRs.setAccountOpenDate("23/11/2021");
        savingAccountInquiryRs.setAccountFreezeStatus("D");
        savingAccountInquiryRs.setAccountStatus("activa");
        savingAccountInquiryRs.setAccountAPY(1000);
        savingAccountInquiryRs.setAccountCloseFlag("dos");
        savingAccountInquiryRs.setInterestCreditAccountIndicator("10%");
        savingAccountInquiryRs.setInterestCreditAccountId("cuenta0");
        savingAccountInquiryRs.setEffectiveAvailableBalance(1000);
        savingAccountInquiryRs.setAvailableBalance(01);
        savingAccountInquiryRs.setClearBalance(1);
        savingAccountInquiryRs.setLienBalance(2);
        savingAccountInquiryRs.setLienBalanceCurrency("2000");
        DataLock dataLock = new DataLock();
        dataLock.setSavingAccountInquiryRs(savingAccountInquiryRs);
        customerResponseJSON2.setData(dataLock);
        Meta meta = new Meta();
        meta.setRequestuuid("String");
        meta.setGlobaluuid("String");
        meta.setContexturl("String");
        customerResponseJSON2.setMeta(meta);
        return customerResponseJSON2;
    }

}

