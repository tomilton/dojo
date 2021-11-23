package co.com.nequi.webclient.defaultdata;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;

public class DataProvider {
    public static CustomerDefaultData customerDefaultDataBank(){
        Integer defaultDataBankId = Integer.valueOf("157");
        CustomerDefaultData customerDefaultData = new CustomerDefaultData();
        customerDefaultData.setNombre("bankId");
        customerDefaultData.setValorDefecto("1600");
        customerDefaultData.setDatoDefectoId(defaultDataBankId);
        customerDefaultData.setServicioId("1");
        return customerDefaultData;
    }

    public static CustomerDefaultData customerDefaultDataCustStatus(){
        CustomerDefaultData customerDefaultData = new CustomerDefaultData();
        customerDefaultData.setNombre("CustStatus");
        customerDefaultData.setValorDefecto("ACTIVE");
        customerDefaultData.setDatoDefectoId(Integer.valueOf("1"));
        customerDefaultData.setServicioId("1");
        return customerDefaultData;
    }
}
