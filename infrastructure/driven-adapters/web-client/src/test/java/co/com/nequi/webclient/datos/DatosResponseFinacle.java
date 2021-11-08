package co.com.nequi.webclient.datos;

import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import co.com.nequi.webclient.json.customer.response.Data;
import co.com.nequi.webclient.json.customer.response.Meta;

import java.util.ArrayList;

public class DatosResponseFinacle {

    private DatosResponseFinacle() {
    }

    public static Meta buildMeta() {
        Meta meta = new Meta();
        meta.setRequestuuid("string");
        meta.setGlobaluuid("string");
        meta.setContexturl("string");
        meta.setErrorDetails(new ArrayList<>());
        return meta;
    }

    public static Data buildData() {
        Data data = new Data();
        data.setCifID("string");
        return data;
    }

    public static CustomerResponseJSON buildCustomerResponseJSON() {
        CustomerResponseJSON customerResponseJSON = new CustomerResponseJSON();
        customerResponseJSON.setMeta(buildMeta());
        customerResponseJSON.setData(buildData());
        return customerResponseJSON;
    }


}
