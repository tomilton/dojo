package co.com.nequi.trace.configuration;

import co.com.nequi.trace.TraceAdapter;
import co.com.nequi.trace.TraceLogBack;
import co.com.nequi.trace.dto.TraceDto;
import co.com.nequi.trace.impl.TraceLogBackImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class TraceInyector {

    @Value("${traceconfig.type}")
    public String typeConfig;

    @Bean
    public TraceAdapter buildTraceAdapter(){

        TraceAdapter<TraceDto> adapter = (traceSave) -> {
         switch (typeConfig){
             case "logback": {
                 buildTraceLogBack(traceSave);
                 break;
             }
             default: {
                 break;
             }

         }
        };
        return adapter;
    }

    private void buildTraceLogBack(TraceDto traceSave){
        TraceLogBack<TraceDto> traceLogBack = new TraceLogBackImpl<>();
        traceLogBack.saveTrace(traceSave);
    }
}
