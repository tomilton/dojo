package co.com.nequi.trace.dto;

import co.com.nequi.trace.builder.TraceBuilder;
import lombok.Getter;

@Getter
public class TraceDto {
    final String messageId;
    final String phone;
    final String region;
    final String operation;
    final String service;
    final String request;
    final String response;

    public TraceDto(TraceBuilder builder) {
        this.messageId = builder.getMessageId();
        this.phone = builder.getPhone();
        this.region = builder.getRegion();
        this.operation = builder.getOperation();
        this.service = builder.getService();
        this.request = builder.getRequest();
        this.response = builder.getResponse();
    }

    public String toString() {
        return String.format("Message Id: %s Phone: %s Region: %s Operation: %s  Service: %s Request: %s Response: %s", messageId, phone, region, operation, service, request, response);
    }
}
