package com.example.michong_pc.tiku.function_activity.Chapter_mode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.michong_pc.tiku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class zhangjiexunlian extends AppCompatActivity {
    private ListView listView;
    private final String[] data = {"第一章  气体","第二章  热力学第一定律","第三章  热力学第二定律","第四章  多组分系统热力学及其在溶液中的应用","第五章  相平衡","第六章  化学平衡","第七章  统计热力学","第八章  电解质溶液"};
    private String[] number={"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四"};
    private String input = "";
    private String result = "";
    private List<String> capter;



   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {

           Log.i("-----",msg.what+"");

           listView.setAdapter(new ArrayAdapter<String>(zhangjiexunlian.this,R.layout.list_item,capter));
       }
   };





    //处理json解析异常
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhangjielianxi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("章节练习");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        capter = new ArrayList<String>();
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url= new URL("http://tk.e8net.cn/ApiCatalog/index");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.setRequestMethod("GET");
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while((input = br.readLine())!=null){
                        result += input;
                    }
                    Log.i("测试",result);

                    String NewResult  = removeBOM(result);

                    JSONObject jsonObject = new JSONObject(NewResult);
                    String rs = jsonObject.getString("msg");
                    Log.i("结果",rs);

                    JSONArray jsonArray = jsonObject.getJSONArray("value");
                    //获取章节数
                    int number = jsonArray.length();
                    Log.i("章节数",number+"");
                    //JSONObject  jo = jsonArray.getJSONObject(1);
                    for(int i =0;i<number;i++){
                        JSONObject  jo = jsonArray.getJSONObject(i);
                        Log.i("第"+i+"条",jo.getString("name"));
                        capter.add(jo.getString("name"));
                    }
                    handler.sendEmptyMessage(0);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("出错","error");
                }
            }
        }.start();

        listView  = (ListView) findViewById(R.id.zhangjielianxi_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                Bundle bundle = new Bundle();
                //设置标题为第几章
                bundle.putString("capter","第"+number[position]+"章");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
