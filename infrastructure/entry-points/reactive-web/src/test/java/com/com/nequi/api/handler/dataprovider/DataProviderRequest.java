package com.com.nequi.api.handler.dataprovider;

import co.com.nequi.api.requestmdw.*;

import java.util.ArrayList;
import java.util.List;

public class DataProviderRequest {
    public static RequestJsonMdw buildRequestFreezeAccountDefaultTest(Object any){
        RequestJsonMdw requestMdw = new RequestJsonMdw();
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
        body.setAny(any);
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    public static RequestJsonMdw buildRequestUnFreezeAccountDefaultTest(Object any){
        RequestJsonMdw requestMdw = new RequestJsonMdw();
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
        body.setAny(any);
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }
}
