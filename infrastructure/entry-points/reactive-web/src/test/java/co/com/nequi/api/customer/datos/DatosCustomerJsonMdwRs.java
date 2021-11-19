package co.com.nequi.api.customer.datos;

import co.com.nequi.api.models.createcustomer.CustomerJsonMdwRs;
import co.com.nequi.api.models.createcustomer.LiteRegistryBrokerRS;

public class DatosCustomerJsonMdwRs {

    private DatosCustomerJsonMdwRs() {
    }

    public static LiteRegistryBrokerRS buildLiteRegistryBrokerRS() {
        LiteRegistryBrokerRS liteRegistryBrokerRS = new LiteRegistryBrokerRS();
        liteRegistryBrokerRS.setCifID("RET0000167");
        return liteRegistryBrokerRS;
    }

    public static CustomerJsonMdwRs buildCustomerJsonMdwRs() {
        CustomerJsonMdwRs customerJsonMdwRs = new CustomerJsonMdwRs();
        customerJsonMdwRs.setLiteRegistryBrokerRS(buildLiteRegistryBrokerRS());
        return customerJsonMdwRs;
    }

}
