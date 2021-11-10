package co.com.nequi.api.config;

import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.trace.TraceAdapter;
import co.com.nequi.trace.builder.TraceBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.log.LogMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.charset.Charset;

@Component
class RequestResponseLoggingFilter implements WebFilter {

    private final TraceAdapter traceAdapter;

    public RequestResponseLoggingFilter(TraceAdapter traceAdapter) {
        this.traceAdapter = traceAdapter;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest httpRequest = exchange.getRequest();
        final String httpUrl = httpRequest.getURI().toString();

        ServerHttpRequestDecorator loggingServerHttpRequestDecorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
            String requestBody = "";
            @Override
            public Flux<DataBuffer> getBody() {
                return super.getBody().doOnNext(dataBuffer -> {
                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                        InputStream targetStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        requestBody = IOUtils.toString(targetStream, Charset.defaultCharset());
                        this.buildRequest(requestBody,httpUrl);
                    } catch (IOException e) {
                        System.out.println("ocurrio un errror en web filter request");
                    }
                });
            }

            public void buildRequest(String request,String httpUrl){
                try {
                    RequestJsonMdw requestJsonMdw = new ObjectMapper().readValue(request, RequestJsonMdw.class);
                    traceAdapter.generate(new TraceBuilder().with(traceBuilder -> {
                        traceBuilder.setOperation("IN - "+httpUrl);
                        traceBuilder.setPhone("TELEFONO");
                        traceBuilder.setRegion(requestJsonMdw.getRequestHeaderOut().getHeader().getMessageContext().getProperty().getItem().stream().filter(pred -> pred.getKey().equals("Region")).findFirst().get().getValue());
                        traceBuilder.setRequest(request);
                        traceBuilder.setMessageId(requestJsonMdw.getRequestHeaderOut().getHeader().getMessageID());
                        traceBuilder.setService(this.getClass().getName());
                    }).createTrace());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };

        ServerHttpResponseDecorator loggingServerHttpResponseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
            String responseBody = "";
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                Mono<DataBuffer> buffer = Mono.from(body);
                return super.writeWith(buffer.doOnNext(dataBuffer -> {
                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                        InputStream targetStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        responseBody = IOUtils.toString(targetStream, Charset.defaultCharset());
                        this.buildResponse(responseBody,httpUrl);
                    } catch (Exception e) {
                        System.out.println("ocurrio un errror en web filter response");
                    }
                }));
            }

            public void buildResponse(String response,String httpUrl){
                try {
                    ResponseJsonMdw responseMdw = new ObjectMapper().readValue(response, ResponseJsonMdw.class);
                    traceAdapter.generate(new TraceBuilder().with(traceBuilder -> {
                        traceBuilder.setOperation("OUT - "+httpUrl);
                        traceBuilder.setPhone("TELEFONO");
                        traceBuilder.setResponse(response);
                        traceBuilder.setService(this.getClass().getName());
                        traceBuilder.setMessageId(responseMdw.getResponseHeaderOut().getHeader().getMessageID());
                    }).createTrace());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };
        return chain.filter(exchange.mutate().request(loggingServerHttpRequestDecorator).response(loggingServerHttpResponseDecorator).build());
    }

}
