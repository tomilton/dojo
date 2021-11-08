package co.com.nequi.trace;

import co.com.nequi.trace.builder.TraceBuilder;
import co.com.nequi.trace.configuration.BeanConfiguration;
import co.com.nequi.trace.configuration.PropertiesConfig;
import co.com.nequi.trace.configuration.TraceInyector;
import co.com.nequi.trace.dto.TraceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootConfiguration
@TestPropertySource(properties = {"traceconfig.type=logback"})
@ComponentScan({"co.com.nequi.trace.configuration"})
@ContextConfiguration(classes = BeanConfiguration.class)
@SpringBootTest
public class TraceAdapterTest {

    @Autowired
    public PropertiesConfig propertiesConfig;

    @Test
    public void whenPropertyProvided_thenProperlyInjected() {
        assertEquals("logback",propertiesConfig.getTypeConfig(),"Son diferentes");
    }

    @Test
    public void inyectTraceAdapterLogBackInstance(){
        TraceInyector traceInyector = new TraceInyector();
        TraceAdapter<TraceDto> traceLogback = traceInyector.buildTraceAdapter();
        assertNotNull(traceLogback);
    }

    @Test
    public void inyectTraceAdapterLogBackInstanceGenerateLog(){
        TraceInyector traceInyector = new TraceInyector();
        traceInyector.propertiesConfig = propertiesConfig;
        TraceAdapter<TraceDto> traceLogback = traceInyector.buildTraceAdapter();
        TraceBuilder builder = new TraceBuilder();
        TraceDto trace = builder.with(traceBuilder -> {
            traceBuilder.setMessageId("test");
            traceBuilder.setOperation("operation");
            traceBuilder.setPhone("phone");
            traceBuilder.setResponse("response");
            traceBuilder.setRegion("region");
            traceBuilder.setRequest("request");
            traceBuilder.setService("service");
        }).createTrace();
        assertNotNull(traceLogback);
        traceLogback.generate(trace);

    }
}
