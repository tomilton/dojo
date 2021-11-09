package co.com.nequi.configuration;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.model.account.dto.FreezeAccountRqDto;
import co.com.nequi.model.requestmdw.RequestMdw;
import co.com.nequi.trace.TraceAdapter;
import co.com.nequi.trace.builder.TraceBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

//@Aspect
@Component
public class TraceAspect {
    @Autowired
    private  TraceAdapter traceAdapter;

    private UUID messageIdAct;

    //@Before(value = "execution(* co.com.nequi.api.AccountHandler.*(..)) && args(serverRequest)")
    public void beforeAdvice(JoinPoint joinPoint, ServerRequest serverRequest) {
        messageIdAct = UUID.randomUUID();
        System.out.println("Before method:" + joinPoint.getSignature());
        String uri = serverRequest.uri().toString();
        String method = serverRequest.methodName();
        //Mono.fromFuture(serverRequest.bodyToMono(JSONObject.class).toFuture()).subscribe(System.out::println);
        System.out.println("validaicon server request"+getPayload(joinPoint));
        traceAdapter.generate(new TraceBuilder().with(traceBuilder -> {
            traceBuilder.setMessageId(messageIdAct.toString());
            traceBuilder.setOperation(uri);
            traceBuilder.setPhone(joinPoint.toString());
            traceBuilder.setResponse("response");
            traceBuilder.setRegion("region");
            traceBuilder.setRequest(getPayload(joinPoint));
            traceBuilder.setService(method);
        }).createTrace());
        System.out.println("request "+serverRequest.toString());
    }

    //@AfterReturning(value = "execution(* co.com.nequi.api.AccountHandler.*(..))",
    //        returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        traceAdapter.generate(new TraceBuilder().with(traceBuilder -> {
            traceBuilder.setMessageId(messageIdAct.toString());
            traceBuilder.setOperation(joinPoint.getSignature().toString());
            traceBuilder.setPhone(joinPoint.toString());
            traceBuilder.setResponse(getPayload(joinPoint));
            traceBuilder.setRegion("region");
            traceBuilder.setRequest("request");
            traceBuilder.setService(joinPoint.toString());
        }).createTrace());
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            builder.append(", ");
        }
        return builder.toString();
    }
}
