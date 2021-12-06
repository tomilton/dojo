package co.com.nequi.processor.services;

import co.com.nequi.model.customerdefaultdata.CustomerDefaultData;
import co.com.nequi.model.customerdefaultdata.gateways.CustomerDefaultDataRepository;
import co.com.nequi.model.processor.IParameter;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.template.DefaultDataKeyProperty;
import co.com.nequi.model.template.PropertyRQ;
import co.com.nequi.model.template.PropertyRS;
import co.com.nequi.model.template.Template;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessParameter {

    @Autowired
    public CustomerDefaultDataRepository customerDefaultDataRepository;

    public Mono<JSONObject> executeOrigin(Template template, RequestMdw requestMdw){
        JSONObject bodyRequest = new JSONObject();
        IParameter<Template,JSONObject>  parameterFunctionOrigin = templateOrigin -> {;
            String templatedDestRq = templateOrigin.getTemplateDestRQ().getBody();
            JSONObject objectProperty = processPropertysIntegrationTypeRq(requestMdw,template);
            bodyRequest.put(templatedDestRq,objectProperty.toString());
            return bodyRequest;
        };
        return Mono.just(parameterFunctionOrigin.execute(template));
    }

    public Mono<JSONObject> executeDest(Template template, String response){
        JSONObject bodyResponse = new JSONObject();
        IParameter<Template,JSONObject>  parameterFunctionDest = templateDest -> {

            String templatedDestRq = templateDest.getTemplateOrigRS().getBody();
            JSONObject objectProperty = processPropertysIntegrationTypeRs(response,template);
            if(objectProperty.keySet().contains(templatedDestRq)){
                return objectProperty;
            }else{
                bodyResponse.put(templatedDestRq,objectProperty.toString());
            }

            return bodyResponse;
        };
        return Mono.just(parameterFunctionDest.execute(template));
    }


    private JSONObject processPropertysIntegrationTypeRq(RequestMdw requestMdw, Template templateOrigin){

        JSONObject objectRQ = new JSONObject();
        Object bodyRequest = requestMdw.getRequestHeaderOut().getBody().getAny();
        JSONObject anyBody = new JSONObject(bodyRequest);
        templateOrigin.getPropertyRQ().forEach(propertyRQ -> {
            HashMap<String,String> objectPropertyMap = buildHashMapPropertyOrig(anyBody,propertyRQ,templateOrigin.getDefaultDataKeys());
            for (Map.Entry<String, String> entry : objectPropertyMap.entrySet()) {
                objectRQ.put(entry.getKey(),entry.getValue());
            }
        });

        return objectRQ;
    }

    private JSONObject processPropertysIntegrationTypeRs(String response, Template templateOrigin){

        JSONObject objectRS = new JSONObject();

        templateOrigin.getPropertyRS().forEach(propertyRS -> {
            HashMap<String,String> objectPropertyMap = buildHashMapPropertyDest(response,propertyRS,templateOrigin.getDefaultDataKeys());
            for (Map.Entry<String, String> entry : objectPropertyMap.entrySet()) {
                objectRS.put(entry.getKey(),entry.getValue());
            }
        });

        return objectRS;
    }

    private HashMap<String,String> buildHashMapPropertyDest(String objectResponse, PropertyRS propertyRS, List<DefaultDataKeyProperty> defaultDataKeyList){
        HashMap<String,String> hashProp = new HashMap<>();
        JSONObject response = new JSONObject(objectResponse);
        JSONObject responseAny = response.getJSONObject("data");

        if(propertyRS.getPropertyOrig().contains(".")){
            //TODO varios niveles de mapeo
            hashProp = buildMultipleMapProperty(responseAny,propertyRS.getPropertyOrig(),propertyRS.getPropertyDest(),defaultDataKeyList,propertyRS.getIntegrationType());
        }else{
            //TODO mapeo de un solo nivel
            String jsonPropertyValue = responseAny.getString(propertyRS.getPropertyOrig());
            Mono<String> value = getValueProperty(propertyRS.getIntegrationType() , propertyRS.getPropertyOrig(),defaultDataKeyList,jsonPropertyValue);
            hashProp.put(propertyRS.getPropertyDest(),value.block());
        }

        return hashProp;
    }

    private HashMap<String,String> buildHashMapPropertyOrig(JSONObject anyBody,PropertyRQ propertyRQ,List<DefaultDataKeyProperty> defaultDataKeyList){
        HashMap<String,String> hashProp = new HashMap<>();

        if(propertyRQ.getPropertyOrig().contains(".")){
            //TODO varios niveles de mapeo
            hashProp = buildMultipleMapProperty(anyBody,propertyRQ.getPropertyOrig(),propertyRQ.getPropertyDest(),defaultDataKeyList,propertyRQ.getIntegrationType());
        }else{
            //TODO mapeo de un solo nivel
            String jsonPropertyValue = anyBody.getString(propertyRQ.getPropertyOrig());
            Mono<String> value = getValueProperty(propertyRQ.getIntegrationType() , propertyRQ.getPropertyOrig(),defaultDataKeyList,jsonPropertyValue);
            hashProp.put(propertyRQ.getPropertyDest(),value.block());
        }

        return hashProp;
    }

    private Mono<String> getValueProperty(String integrationType , String propertyName, List<DefaultDataKeyProperty> defaultDataKeyList,String valuePage){
        Mono<String> valueInit = Mono.empty();
        if(integrationType.equals("copyPageLogic")){
            //TODO consultar valor proveniente del request
            valueInit = Mono.just(valuePage);
        }else if(integrationType.equals("integrationLogic")){
            // TODO logica de ejecucion de metodos por referencia
        }else if(integrationType.equals("defaultData")){
            // TODO logica de obtencion de dato por cache
            valueInit = getDefaultDataValue(defaultDataKeyList,propertyName);
        }
        return valueInit;
    }

    private Mono<String> getDefaultDataValue(List<DefaultDataKeyProperty> defaultDataKeyList,String propertyName){
        Mono<CustomerDefaultData> customerDefaultData = this.customerDefaultDataRepository.getDefaultDataId(defaultDataKeyList.stream()
                .filter(defaultDataKeyProperty -> defaultDataKeyProperty.getName().equals(propertyName)).map(DefaultDataKeyProperty::getDefaultDataId).findFirst().get(),defaultDataKeyList.stream()
                .filter(defaultDataKeyProperty -> defaultDataKeyProperty.getName().equals(propertyName)).map(DefaultDataKeyProperty::getServiceId).findFirst().get());
        return  customerDefaultData.map(data -> data.getValorDefecto());
    }

    private HashMap<String,String> buildMultipleMapProperty(JSONObject jsonObject,String propertyName,String propertyDest,List<DefaultDataKeyProperty> defaultDataKeyList,String integrationType){
        //String[] levelList = propertyName.split(".");
        //HashMap<String,String> before = null;
        HashMap<String,String> level = null;
        String jsonPropertyValue = getLevelEndValueJson(jsonObject, propertyName);
        if(propertyDest.contains(".")){
            //TODO construir objeto por referencia
            /**for(int i= levelList.length; i <= 0 ; i--){
             String prop = levelList[i];
             if(i == levelList.length){
             Mono<String> value = getValueProperty(integrationType, prop,defaultDataKeyList,jsonPropertyValue);
             before = new HashMap<>();
             before.put(prop,value.block());
             }else{
             level = new HashMap<>();
             level.put(prop,new JSONObject(before).toString());
             }
             before = level;
             }*/
        }else{
            level = new HashMap<>();
            level.put(propertyDest,jsonPropertyValue);
        }
        return level;
    }

    private String getLevelEndValueJson(JSONObject body, String propName){
        String value = "";
        JSONObject levelAct = body;
        String[] levelList = propName.split("\\.");
        for(int i=0; i <= levelList.length ; i++){
            String prop = levelList[i];
            if(i == (levelList.length - 1)){
                value = levelAct.getString(prop);
                break;
            }
            levelAct = levelAct.getJSONObject(prop);
        }
        return value;
    }

}
