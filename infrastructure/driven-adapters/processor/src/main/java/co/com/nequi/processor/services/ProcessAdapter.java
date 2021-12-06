package co.com.nequi.processor.services;

import co.com.nequi.model.account.dto.FreezeAccountRs;
import co.com.nequi.model.processor.IprocessAdapter;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.model.template.Template;
import co.com.nequi.model.template.gateways.TemplateRepository;
import co.com.nequi.processor.services.util.ResponseUtil;
import org.json.JSONObject;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProcessAdapter implements IprocessAdapter {

    @Autowired
    public ProcessParameter processParameter;

    @Autowired
    public TemplateRepository templateService;

    @Autowired
    public SendFinacle sendFinacle;

    @Autowired
    public ObjectMapper mapper;

    @Override
    public Mono<ResponseMdw> execute(RequestMdw request) {

        Mono<Template> template = templateService.getById("234");
        Mono<JSONObject> buildBodyRequest = template.flatMap(templateBuild -> processParameter.executeOrigin(templateBuild,request));
        Mono<String> finacleResponse = Mono.zip(template,buildBodyRequest).flatMap(t -> {
            Template templateMap = t.getT1();
            JSONObject bodyRequestMap =  t.getT2();
            return sendFinacle.execute(templateMap,bodyRequestMap);
        });
        Mono<JSONObject> bodyResponse = Mono.zip(template,finacleResponse).flatMap(tEnd -> {
            Template templateMap = tEnd.getT1();
            String response = tEnd.getT2();
            return processParameter.executeDest(templateMap,response);
        });

        return bodyResponse.map((responseService) -> ResponseUtil.buildResponseSuccess(responseService.toString(),request));
    }
}
