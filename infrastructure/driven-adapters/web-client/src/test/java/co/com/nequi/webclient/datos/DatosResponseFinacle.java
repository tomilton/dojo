package co.com.nequi.webclient.datos;


import co.com.nequi.model.responsefinacle.customer.*;

import java.util.ArrayList;
import java.util.List;

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

    public static CustomerResponseFinacle buildCustomerResponseSuccesJSON() {
        CustomerResponseFinacle customerResponseJSON = new CustomerResponseFinacle();
        customerResponseJSON.setMeta(buildMeta());
        customerResponseJSON.setData(buildData());
        return customerResponseJSON;
    }

    public static CustomerResponseFinacle buildCustomerResponseErrorJSON() {
        CustomerResponseFinacle customerResponseJSON = new CustomerResponseFinacle();
        customerResponseJSON.setMeta(buildMetaWithErros());
        customerResponseJSON.setData(buildData());
        return customerResponseJSON;
    }


}
