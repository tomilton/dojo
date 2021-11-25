package co.com.nequi.webclient.services.datos;
import co.com.nequi.model.responsefinacle.detailprueba.DataData;
import co.com.nequi.model.responsefinacle.detailprueba.Meta;
import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle2;
import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON2;
import co.com.nequi.model.responsefinacle.detailprueba.DataLock;
import co.com.nequi.model.responsefinacle.customer.SavingAccountInquiryRs;

public class Response {


    public static CustomerResponseFinacle2 buildsucessFinacleJSON() {
        CustomerResponseFinacle2 customerResponseFinacle2 = new CustomerResponseFinacle2();
        Meta meta = new Meta();
        meta.setRequestuuid("String");
        meta.setGlobaluuid("String");
        meta.setContexturl("String");

        DataLock dataLock = new DataLock();
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

        dataLock.setSavingAccountInquiryRs(savingAccountInquiryRs);
        customerResponseFinacle2.setMeta(meta);
        customerResponseFinacle2.setData(dataLock);

        return customerResponseFinacle2;
    }
}
