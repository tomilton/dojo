package co.com.nequi.usecase.createcustomer.datos;


import co.com.nequi.model.responsefinacle.customer.CustomerResponseFinacle;
import co.com.nequi.model.responsefinacle.customer.Data;
import co.com.nequi.model.responsefinacle.customer.ErrorDetail;
import co.com.nequi.model.responsefinacle.customer.Meta;

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
        data.setCifID("NP16000000239824");
        return data;
    }

    public static CustomerResponseFinacle buildCustomerResponseSuccess() {
        CustomerResponseFinacle customerResponse = new CustomerResponseFinacle();
        customerResponse.setMeta(buildMeta());
        customerResponse.setData(buildData());
        return customerResponse;
    }


}
