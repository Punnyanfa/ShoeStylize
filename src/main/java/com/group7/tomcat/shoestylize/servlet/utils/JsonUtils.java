package com.group7.tomcat.shoestylize.servlet.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtils {

    public static JsonObject parseJsonRequest(HttpServletRequest request) throws IOException {
        try {
            // Read the request body
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Parse the JSON data using Gson
            JsonParser parser = new JsonParser();
            return parser.parse(stringBuilder.toString()).getAsJsonObject();
        } catch (Exception e) {
            // Handle the exception or log it as needed
            throw new IOException("Failed to parse JSON request", e);
        }
    }
}