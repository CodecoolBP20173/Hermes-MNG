package com.mng.hermes.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Component
public class HttpRequest {

    private static String USERINFO_ENDPOINT = "https://danielcs88.eu.auth0.com/userinfo";

    public String fetchUserData(String token) {
        try {
            URL url = new URL(USERINFO_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Could not reach authentication servers!");
            }
            String data = processResponse(conn);
            conn.disconnect();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String processResponse(HttpURLConnection conn) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream()
        ));
        String input;
        StringBuilder sb = new StringBuilder();
        while ((input = reader.readLine()) != null) {
            sb.append(input);
        }
        return sb.toString();
    }

}
