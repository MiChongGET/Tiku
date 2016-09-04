package com.example.michong_pc.tiku.dom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/2.
 */
public class JsonToString {


    public static void getString(){
        try {
            JSONObject obj=new JSONObject("");

             String error_code= obj.getString("error_code");


            JSONArray array=obj.getJSONArray("result");

            for (int i=0, len=array.length();i<len;i++){
                     JSONObject jSONObject=   array.getJSONObject(i);
                     String id=jSONObject.getString("id");
                     String qusetion = jSONObject.getString("question");
                     String answer = jSONObject.getString("answer");
                System.out.println(id+"\n"+qusetion+"\n"+answer);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
