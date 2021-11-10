package co.com.nequi.trace.impl;

import co.com.nequi.trace.TraceLogBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TraceLogBackImpl<T> implements TraceLogBack<T> {
    private final Logger LOGGER =
            LoggerFactory.getLogger(this.getClass());
    @Override
    public void saveTrace(T trace) {
        LOGGER.info(trace.toString());
    }
}
