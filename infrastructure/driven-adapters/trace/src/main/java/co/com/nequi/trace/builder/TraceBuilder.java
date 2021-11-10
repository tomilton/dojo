package co.com.nequi.trace.builder;

import co.com.nequi.trace.dto.TraceDto;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
public class TraceBuilder {
    String messageId;
    String phone;
    String region;
    String operation;
    String service;
    String request;
    String response;

    public TraceBuilder with(Consumer<TraceBuilder> buildFields){
        buildFields.accept(this);
        return this;
    }

    public TraceDto createTrace() {
        return new TraceDto(this);
    }


}
