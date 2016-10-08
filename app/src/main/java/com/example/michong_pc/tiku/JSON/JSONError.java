package com.example.michong_pc.tiku.JSON;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/10/8.
 */

public class JSONError  {
   //处理json解析异常的方法
    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }

        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
}
