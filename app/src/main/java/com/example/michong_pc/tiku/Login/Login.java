package com.example.michong_pc.tiku.Login;

import android.content.Intent;
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
import com.example.michong_pc.tiku.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.arg1){
                case 0 :
                    Toast.makeText(Login.this,"登陆成功！！！",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this, Activity_home.class));
                    break;
                case 1:
                    Toast.makeText(Login.this,"账号或密码错误,请重新输入！！！",Toast.LENGTH_LONG).show();
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



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuehao = accountedit.getText().toString();
                pwd = pwdedit.getText().toString();
                //Log.i("学号和密码",xuehao+"  "+pwd);
                url ="http://115.159.153.147/jw.php?xuehao="+xuehao+"&password="+pwd;
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
            String result="";
            System.out.println(url);
            URL url2 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);

            while ((input = bufferedReader.readLine()) != null) {
                //得到整个页面的字符
                result += input;
            }
            Log.i("登录","true");

            bufferedReader.close();
            isr.close();

            //获取message的标识
            Message message  = new Message();
            if(result.equals("true")){
                Log.i("是否成功？","true");
                message.arg1=0;
            }
            else if (result.equals("false")){
                message.arg1=1;
            }

            handler.sendMessage(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
  }
}
