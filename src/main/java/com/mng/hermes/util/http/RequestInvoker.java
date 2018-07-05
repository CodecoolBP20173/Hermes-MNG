package com.mng.hermes.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestInvoker implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestInvoker.class);
    private HttpRequestBuilder http = new HttpRequestBuilder();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO: 2018. 07. 05. Checking args to prevent errors

        String name = method.getName();
        HttpURLConnection connection;
        LOGGER.info("\nInvoked method: {}", name);

        Matcher keyMatcher = Pattern.compile("^(get|post|put|delete)").matcher(name);
        if (!keyMatcher.find()) {
            throw new InvalidMethodSignature("Method needs to start with: get, post, put, or delete");
        }
        String requestMethod = keyMatcher.group(1);
        LOGGER.info("REQUEST METHOD: " + requestMethod);

        boolean convertFromJson = name.contains("Json");
        LOGGER.info("Json flag: " + convertFromJson);

        keyMatcher = Pattern.compile("With([a-zA-z]{6,})$").matcher(name);
        if (keyMatcher.find()) {
            String optionsRaw = keyMatcher.group(1);
            List<String> optionNames = Arrays.asList(optionsRaw.split("And"));
            LOGGER.info(optionNames.toString());

            int queryIndex = optionNames.indexOf("Query");
            if (queryIndex != -1) {
                Map<String, String> queries = (Map)args[queryIndex + 1];
                http.initConnection(args[0].toString(), requestMethod)
            }

            for (String option : optionNames) {

                switch (option) {
                    case "Headers":

                        break;
                    case "Query":
                        break;
                    case "Bearer":
                        break;
                    case "Fragment":
                        break;
                }

            }
        }

        return null;
    }
}
