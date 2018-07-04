package com.mng.hermes.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestInvoker implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestInvoker.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());
        System.out.println("\n\n\n");
        if (method.getName().equals("foo")) {
            LOGGER.info("BAR");
        }
        if (method.getName().equals("bar")) {
            LOGGER.info(args[0].toString());
        }
        System.out.println("\n\n\n");
        return null;
    }
}
