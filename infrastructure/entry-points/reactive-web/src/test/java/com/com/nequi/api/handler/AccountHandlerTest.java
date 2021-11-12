package com.com.nequi.api.handler;

import co.com.nequi.api.AccountHandler;
import co.com.nequi.api.config.ReflectionUtils;
import co.com.nequi.api.requestmdw.RequestJsonMdw;
import co.com.nequi.api.requestmdw.RequestHeaderOut;
import co.com.nequi.api.requestmdw.Header;
import co.com.nequi.api.requestmdw.Destination;
import co.com.nequi.api.requestmdw.SecurityCredential;
import co.com.nequi.api.requestmdw.MessageContext;
import co.com.nequi.api.requestmdw.Property;
import co.com.nequi.api.requestmdw.Item;
import co.com.nequi.api.requestmdw.Body;
import co.com.nequi.api.responsemdw.ResponseJsonMdw;
import co.com.nequi.model.account.dto.*;
import co.com.nequi.model.requestmdw.*;
import co.com.nequi.model.responsemdw.ResponseHeaderOut;
import co.com.nequi.model.responsemdw.ResponseMdw;
import co.com.nequi.trace.TraceAdapter;
import co.com.nequi.usecase.freezeaccount.FreezeAccountUseCase;
import co.com.nequi.usecase.unfreezeaccount.UnFreezeAccountUseCase;
import com.com.nequi.api.handler.dataprovider.DataProviderRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootConfiguration
@ComponentScan({"co.com.nequi.api.requestmdw"})
@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
public class AccountHandlerTest {

    @Mock
    private FreezeAccountUseCase freezeUserCase;

    @Mock
    private UnFreezeAccountUseCase unFreezeAccountUseCase;

    @InjectMocks
    private AccountHandler accountHandler;

    @Test
    public void freezeAccountRequest() throws Exception {
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        RequestJsonMdw requestMdw = DataProviderRequest.buildRequestFreezeAccountDefaultTest(freezeAccountRQ);
        ServerRequest serverRequestMock = mock(ServerRequest.class);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(serverRequestMock.bodyToMono(RequestJsonMdw.class)).thenReturn(Mono.just(requestMdw));
        when(mapper.map(any(),eq(RequestMdw.class))).thenReturn(buildRequestMdw());
        when(mapper.map(any(),eq(FreezeAccountRqDto.class))).thenReturn(buildFreezeAccountRqDto());
        when(mapper.map(any(),eq(ResponseJsonMdw.class))).thenReturn(buildResponseJsonMdwOK());
        when(freezeUserCase.freezeAccount(any())).thenReturn(Mono.just(buildResponseMdw()));
        ReflectionUtils.setFinalStaticField(AccountHandler.class, "mapper", mapper,accountHandler);
        ReflectionUtils.setFinalStaticField(AccountHandler.class, "freezeUserCase", freezeUserCase,accountHandler);
        Mono<ServerResponse> responseHandler = accountHandler.freezeAccount(serverRequestMock);
        StepVerifier.create(responseHandler)
                .consumeNextWith(response -> {
                    assertNotNull(response);
                    assertNotNull(response.statusCode());
                    assertEquals(response.statusCode(), HttpStatus.CREATED);
                }).verifyComplete();

    }

    @Test
    public void unFreezeAccountRequest() throws Exception {
        UnFreezeAccountBrokerRQ unFreezeAccountBrokerRQ = new UnFreezeAccountBrokerRQ("0100232484","14");
        RequestJsonMdw requestMdw = DataProviderRequest.buildRequestUnFreezeAccountDefaultTest((unFreezeAccountBrokerRQ));
        ServerRequest serverRequestMock = mock(ServerRequest.class);
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(serverRequestMock.bodyToMono(RequestJsonMdw.class)).thenReturn(Mono.just(requestMdw));
        when(mapper.map(any(),eq(RequestMdw.class))).thenReturn(buildRequestMdw());
        when(mapper.map(any(),eq(UnFreezeAccountBrokerRQ.class))).thenReturn(buildUnFreezeAccountBrokerRQ());
        when(mapper.map(any(),eq(ResponseJsonMdw.class))).thenReturn(buildResponseJsonMdwOK());
        when(unFreezeAccountUseCase.unFreezeAccount(any())).thenReturn(Mono.just(buildResponseMdw()));
        ReflectionUtils.setFinalStaticField(AccountHandler.class, "mapper", mapper,accountHandler);
        ReflectionUtils.setFinalStaticField(AccountHandler.class, "unFreezeAccountUseCase", unFreezeAccountUseCase,accountHandler);
        Mono<ServerResponse> responseHandler = accountHandler.unFreezeAccount(serverRequestMock);
        StepVerifier.create(responseHandler)
                .consumeNextWith(response -> {
                    assertNotNull(response);
                    assertNotNull(response.statusCode());
                    assertEquals(response.statusCode(), HttpStatus.CREATED);
                }).verifyComplete();

    }

    private ResponseMdw buildResponseMdw() {
        ResponseMdw responseMdw = ResponseMdw.builder().build();
        ResponseHeaderOut responseHeaderOut = ResponseHeaderOut.builder().build();
        responseMdw.setResponseHeaderOut(responseHeaderOut);
        return responseMdw;
    }

    private ResponseJsonMdw buildResponseJsonMdwOK(){
        ResponseJsonMdw responseJsonMdw = new ResponseJsonMdw();
        co.com.nequi.api.responsemdw.ResponseHeaderOut responseHeaderOut = new co.com.nequi.api.responsemdw.ResponseHeaderOut();
        co.com.nequi.api.responsemdw.Body body = new co.com.nequi.api.responsemdw.Body();
        FreezeAccountRs freezeAccountRs = new FreezeAccountRs("");
        body.setAny(freezeAccountRs);
        responseHeaderOut.setBody(body);
        responseJsonMdw.setResponseHeaderOut(responseHeaderOut);
        return responseJsonMdw;
    }

    private RequestMdw buildRequestMdw(){
        RequestMdw requestMdw = new RequestMdw();
        co.com.nequi.model.requestmdw.RequestHeaderOut requestHeaderOut = new co.com.nequi.model.requestmdw.RequestHeaderOut();
        co.com.nequi.model.requestmdw.Body body = new co.com.nequi.model.requestmdw.Body();
        body.setAny(buildFreezeAccountRqDto());
        requestHeaderOut.setBody(body);
        requestMdw.setRequestHeaderOut(requestHeaderOut);
        return requestMdw;
    }

    private FreezeAccountRqDto buildFreezeAccountRqDto(){
        FreezeAccountRqDto dtoRequest = new FreezeAccountRqDto();
        FreezeAccountRQ freezeAccountRQ = new FreezeAccountRQ();
        freezeAccountRQ.setAccountNumber("87052427983");
        freezeAccountRQ.setReasonCode("10");
        freezeAccountRQ.setFreezeCode("D");
        dtoRequest.setFreezeAccountRQ(freezeAccountRQ);
        return dtoRequest;
    }

    private UnFreezeAccountBrokerRQ buildUnFreezeAccountBrokerRQ(){
        return new UnFreezeAccountBrokerRQ("0100232484","14");
    }

}
