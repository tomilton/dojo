package co.com.nequi.usecase.dataprovider;

import co.com.nequi.model.account.dto.FreezeAccountRQ;
import co.com.nequi.model.account.dto.UnFreezeAccountRq;
import co.com.nequi.model.account.dto.UnFreezeAccountRqCustomData;
import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.requestmdw.*;

import java.util.ArrayList;
import java.util.List;

public class DataProviderRequest {
    public static RequestMdw buildRequestFreezeAccountDefaultTest(){
        RequestMdw requestMdw = new RequestMdw();
        RequestHeaderOut requestHeaderOut = new RequestHeaderOut();
        SecurityCredential securityCredential = new SecurityCredential();
        securityCredential.setUserName("BROKER");
        securityCredential.setUserToken("TOKEN");
        Header header = new Header();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setSecurityCredential(securityCredential);
        Destination destination = new Destination();
        destination.setName("Account");
        destination.setNamespace("http://co.bancaDigital/nequi/services/AccountServices/Account/v1.0");
        destination.setOperation("freezeAccount");
        header.setDestination(destination);
        MessageContext messageContext = new MessageContext();
        Property property = new Property();
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setKey("RQ");
        item1.setValue("freezeAccountRQ");
        items.add(item1);
        Item item2 = new Item();
        item2.setKey("RS");
        item2.setValue("freezeAccountRS");
        items.add(item2);
        Item item3 = new Item();
        item3.setKey("Region");
        item3.setValue("C001");
        items.add(item3);
        Item item4 = new Item();
        item4.setKey("appType");
        item4.setValue("Nequi");
        items.add(item4);
        property.setItem(items);
        messageContext.setProperty(property);
        header.setMessageContext(messageContext);
        requestHeaderOut.setHeader(header);
        Body body = new Body();
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        body.setAny(freezeAccountRQ);
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    public static RequestMdw buildRequestUnFreezeAccountDefaultTest(){
        RequestMdw requestMdw = new RequestMdw();
        RequestHeaderOut requestHeaderOut = new RequestHeaderOut();
        SecurityCredential securityCredential = new SecurityCredential();
        securityCredential.setUserName("BROKER");
        securityCredential.setUserToken("TOKEN");
        Header header = new Header();
        header.setSystemID("MF-001");
        header.setMessageID("1635390551131");
        header.setInvokerDateTime("2021-10-27 22:09:10");
        header.setSecurityCredential(securityCredential);
        Destination destination = new Destination();
        destination.setName("Account");
        destination.setNamespace("http://co.bancaDigital/nequi/services/AccountServices/Account/v1.0");
        destination.setOperation("unfreezeAccount");
        header.setDestination(destination);
        MessageContext messageContext = new MessageContext();
        Property property = new Property();
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setKey("RQ");
        item1.setValue("unfreezeAccountBrokerRQ");
        items.add(item1);
        Item item2 = new Item();
        item2.setKey("RS");
        item2.setValue("unfreezeAccountBrokerRS");
        items.add(item2);
        Item item3 = new Item();
        item3.setKey("Region");
        item3.setValue("C001");
        items.add(item3);
        Item item4 = new Item();
        item4.setKey("appType");
        item4.setValue("Nequi");
        items.add(item4);
        property.setItem(items);
        messageContext.setProperty(property);
        header.setMessageContext(messageContext);
        requestHeaderOut.setHeader(header);
        Body body = new Body();
        UnFreezeAccountRq unfreezeAccountRQ = new UnFreezeAccountRq();
        unfreezeAccountRQ.setAccountId("1225");
        unfreezeAccountRQ.setBankId("1025");
        UnFreezeAccountRqCustomData unFreezeAccountRqCustomData = new UnFreezeAccountRqCustomData();
        unFreezeAccountRqCustomData.setFreezeReasonCode("D");
        unfreezeAccountRQ.setAccountUnFreezeRq_Customdata(unFreezeAccountRqCustomData);
        body.setAny(unfreezeAccountRQ);
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    public static List<CustomerDefaultData> buildCustomerDefaultDataList(){
        List<CustomerDefaultData> customerDefaultDataList = new ArrayList<>();
        customerDefaultDataList.add(buildCustomerDefaultData());
        return customerDefaultDataList;
    }

    public static CustomerDefaultData buildCustomerDefaultData(){
        CustomerDefaultData customerDefaultData = new CustomerDefaultData();
        customerDefaultData.setDatoDefectoId(Integer.valueOf(157));
        customerDefaultData.setNombre("bankId");
        return customerDefaultData;
    }
}
