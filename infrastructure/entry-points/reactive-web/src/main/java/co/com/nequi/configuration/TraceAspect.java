package co.com.nequi.configuration;

import co.com.nequi.trace.TraceAdapter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Aspect
@Component
public class TraceAspect {
    private TraceAdapter traceAdapter;

    @Before(value = "execution(* co.com.nequi.api.AccountHandler.*(..)) && args(serverRequest)")
    public void beforeAdvice(JoinPoint joinPoint, ServerRequest serverRequest) {
        System.out.println("Before method:" + joinPoint.getSignature());

        System.out.println("request "+serverRequest.toString());
    }

    @After(value = "execution(* co.com.nequi.api.AccountHandler.*(..)) && args(serverRequest)")
    public void afterAdvice(JoinPoint joinPoint, ServerRequest serverRequest) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("response service "+serverRequest.toString());
    }
}
