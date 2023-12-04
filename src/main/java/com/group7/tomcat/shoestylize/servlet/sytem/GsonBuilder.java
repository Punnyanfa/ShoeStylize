package com.group7.tomcat.shoestylize.servlet.sytem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Map;

public class GsonBuilder {

    private JsonObject jsonObject;

    public GsonBuilder() {
        jsonObject = new JsonObject();
    }

    public GsonBuilder set(String key, String value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public GsonBuilder set(String key, int value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public GsonBuilder set(String key, double value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public GsonBuilder set(String key, boolean value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public GsonBuilder set(String key, Map<? extends Object, ? extends Object> map) {
        JsonObject obj = new JsonObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String k = entry.getKey().toString();
            Object value = entry.getValue();

            if (value instanceof Boolean) {
                obj.addProperty(k, (Boolean) value);
            } else if (value instanceof Number) {
                obj.addProperty(k, (Number) value);
            } else if (value instanceof String) {
                obj.addProperty(k, (String) value);
            } else if (value instanceof Character) {
                obj.addProperty(k, (Character) value);
            } else {
            }
        }

        jsonObject.add(key, obj);
        // Add the JsonObject to your GsonBuilder or use it as needed
        return this;
    }

    public GsonBuilder set(String key, JsonArray arr) {
        jsonObject.add(key, arr);
        return this;
    }

    public String build() {
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }
    
    public JsonObject buildJson() {
        return jsonObject;
    }

}
