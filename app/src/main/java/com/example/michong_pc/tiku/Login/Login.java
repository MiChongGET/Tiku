package com.example.michong_pc.tiku.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michong_pc.tiku.Activity_home;
import com.example.michong_pc.tiku.JSON.HttpUtils;
import com.example.michong_pc.tiku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.example.michong_pc.tiku.JSON.JSONError.removeBOM;

public class Login extends AppCompatActivity  {

    private Button button;
    private EditText accountedit;
    private EditText pwdedit;
    private String xuehao;
    private String pwd;
    private String input = "";
    private String result = "13223";
    private String  url = null;
    private String trues = "ture";
    private String num;
    private String name;
    private  SharedPreferences sharedPreferences;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.arg1){
                case 0 :
                    Toast.makeText(Login.this,"登陆成功！！！",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this, Activity_home.class));
                    finish();
                    break;
                case 1:
                    Toast.makeText(Login.this,"账号或密码错误,请重新输入！！！",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(Login.this,"未注册，无法使用",Toast.LENGTH_LONG).show();
                    break;

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountedit = (EditText) findViewById(R.id.accountEdit);
        pwdedit = (EditText) findViewById(R.id.pwdEdit);
        button = (Button) findViewById(R.id.login);


        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuehao = accountedit.getText().toString();
                pwd = pwdedit.getText().toString();
                //Log.i("学号和密码",xuehao+"  "+pwd);
                url ="http://tk.e8net.cn/ApiLogin/login?xuehao="+xuehao+"&password="+pwd;
                Thread t = new Thread(new Data());
                t.start();
                //Log.i("测试",result);
            }
        });
    }


class Data implements  Runnable{
    @Override
    public void run() {
        try {
            String result= HttpUtils.get(url);
            String NewResult  =removeBOM(result);
            JSONObject jo=new JSONObject(NewResult);
            String status = jo.getString("status");
            //获取message的标识
            Message message  = new Message();
            if(status.equals("true")){
                Log.i("是否成功？","true");
                JSONObject ja = jo.getJSONObject("values");
                num= ja.getString("num");
                name = ja.getString("name");

                //存储登陆的信息
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("num",num);
                editor.commit();


                Log.i("登录信息：","学号"+num+"姓名"+name);

                message.arg1=0;
            }
            else if (status.equals("false")){
                message.arg1=1;
            }
            else {
                message.arg1=2;
            }
            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
  }
}
