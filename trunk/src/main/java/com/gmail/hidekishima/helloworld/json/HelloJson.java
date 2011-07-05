package com.gmail.hidekishima.helloworld.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HelloJson {

  public static void main(String[] args) {
    try {
      String response = "{ id:1, name:\"aaa\" }";
      JSONObject json = new JSONObject(response);
      System.out.println(json.get("id"));
      System.out.println(json.get("name"));
      JSONArray array = new JSONArray();
      array.put("v1");
      array.put("v2");
      json.put("id", 2);
      json.put("type", array);
      System.out.println(json.toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    
  }
  
}
