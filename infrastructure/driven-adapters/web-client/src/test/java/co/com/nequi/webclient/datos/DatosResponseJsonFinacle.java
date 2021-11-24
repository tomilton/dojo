package co.com.nequi.webclient.datos;

import co.com.nequi.webclient.json.customer.response.CustomerResponseJSON;
import co.com.nequi.webclient.json.customer.response.Data;
import co.com.nequi.webclient.json.customer.response.ErrorDetail;
import co.com.nequi.webclient.json.customer.response.Meta;

import java.util.ArrayList;
import java.util.List;

public class DatosResponseJsonFinacle {

    private DatosResponseJsonFinacle() {
    }

    public static Meta buildMeta() {
        Meta meta = new Meta();
        meta.setRequestuuid("string");
        meta.setGlobaluuid("string");
        meta.setContexturl("string");
        meta.setErrorDetails(new ArrayList<>());
        return meta;
    }

    public static Meta buildMetaWithErros() {
        Meta meta = new Meta();
        meta.setRequestuuid("string");
        meta.setGlobaluuid("string");
        meta.setContexturl("string");
        List<ErrorDetail> errorDetailLis = new ArrayList<>();
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorcode("500");
        errorDetail.setErrordesc("NO SE PUDO CREAR EL CLIENTE");
        errorDetailLis.add(errorDetail);
        meta.setErrorDetails(errorDetailLis);
        return meta;
    }

    public static Data buildData() {
        Data data = new Data();
        data.setCifID("string");
        return data;
    }

    public static CustomerResponseJSON buildCustomerResponseSuccesJSON() {
        CustomerResponseJSON customerResponseJSON = new CustomerResponseJSON();
        customerResponseJSON.setMeta(buildMeta());
        customerResponseJSON.setData(buildData());
        return customerResponseJSON;
    }

    public static CustomerResponseJSON buildCustomerResponseErrorJSON() {
        CustomerResponseJSON customerResponseJSON = new CustomerResponseJSON();
        customerResponseJSON.setMeta(buildMetaWithErros());
        customerResponseJSON.setData(buildData());
        return customerResponseJSON;
    }


}
