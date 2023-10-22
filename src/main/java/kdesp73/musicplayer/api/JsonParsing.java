/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author konstantinos
 */
public class JsonParsing {
    public static Object getJsonValue(String body, String key1) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        
        if(jsonObject == null) return null;
        if(jsonObject.get(key1) == null) return null;
        
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());

        return innerJSON;
    }

    public static Object getJsonValue(String body, String key1, String key2) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());

        return innerJSON.get(key2);
    }

    public static Object getJsonValue(String body, String key1, String key2, String key3) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());
        JSONObject inner2JSON = (JSONObject) JSONValue.parse(innerJSON.get(key2).toString());

        return inner2JSON.get(key3);
    }
    public static Object getJsonValue(String body, String key1, String key2, String key3, String key4) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());
        JSONObject inner2JSON = (JSONObject) JSONValue.parse(innerJSON.get(key2).toString());
        JSONObject inner3JSON = (JSONObject) JSONValue.parse(inner2JSON.get(key3).toString());

        return inner3JSON.get(key4);
    }
    public static Object getJsonValue(String body, String key1, String key2, String key3, String key4, String key5) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());
        JSONObject inner2JSON = (JSONObject) JSONValue.parse(innerJSON.get(key2).toString());
        JSONObject inner3JSON = (JSONObject) JSONValue.parse(inner2JSON.get(key3).toString());
        JSONObject inner4JSON = (JSONObject) JSONValue.parse(inner3JSON.get(key4).toString());

        return inner4JSON.get(key5);
    }
    public static Object getJsonValue(String body, String key1, String key2, String key3, String key4, String key5, String key6) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(body);
        JSONObject innerJSON = (JSONObject) JSONValue.parse(jsonObject.get(key1).toString());
        JSONObject inner2JSON = (JSONObject) JSONValue.parse(innerJSON.get(key2).toString());
        JSONObject inner3JSON = (JSONObject) JSONValue.parse(inner2JSON.get(key3).toString());
        JSONObject inner4JSON = (JSONObject) JSONValue.parse(inner3JSON.get(key4).toString());
        JSONObject inner5JSON = (JSONObject) JSONValue.parse(inner4JSON.get(key5).toString());

        return inner5JSON.get(key6);
    }
}
