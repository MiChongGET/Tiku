package com.example.michong_pc.tiku.JSON;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/8.
 */

public class JsonUse {


    public static void JsonUse(String s)
            throws JSONException {

        JSONObject jsonObject = new JSONObject(s);
        String rs = jsonObject.getString("msg");
        JSONArray jsonArray = jsonObject.getJSONArray("value");

    }
}
