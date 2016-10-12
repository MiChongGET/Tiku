package com.example.michong_pc.tiku.function_activity.Formulary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.michong_pc.tiku.JSON.HttpUtils;
import com.example.michong_pc.tiku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.github.kexanie.library.MathView;

import static com.example.michong_pc.tiku.JSON.JSONError.removeBOM;

public class Formulay_content extends AppCompatActivity {

    private String ID;
    private String formulary_url;
    private String result;
    private StringBuilder sb;
    private MathView mMathView;
    private ScrollView scrollView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

                    mMathView.setText(sb.toString());

            //mListView.setAdapter(new ArrayAdapter<String>(Formulay_content.this,R.layout.list_formulary,mList));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulay_content);
        //传递第几套的数值
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ID = b.getString("id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle(b.getString("capter"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        formulary_url = "http://tk.e8net.cn/ApiFormula/getAllFormula/id/"+ID;

//        mList = new ArrayList<String>();
//        mListView = (ListView) findViewById(R.id.formulary_content);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        mMathView = (MathView) findViewById(R.id.formulary_ban);
        WebSettings mWebSettings = mMathView.getSettings();
        mWebSettings.setSupportZoom(true);
//        mWebSettings.setDefaultFontSize(18);
        mMathView.setInitialScale(300);
        //解决scrollview和webview冲突
//        mMathView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                if (event.getAction() == MotionEvent.ACTION_UP)
//                    scrollView.requestDisallowInterceptTouchEvent(false);
//                else
//                    scrollView.requestDisallowInterceptTouchEvent(true);
//
//                return false;
//            }
//
//            });


        sb = new StringBuilder();
        Log.i("结果","我是谁？？？");

        new Thread(){
            @Override
            public void run() {
                try {

                    result = HttpUtils.get(formulary_url);
                    String NewResult  =removeBOM(result);
                    JSONObject jsonObject = new JSONObject(NewResult);
                    String rs = jsonObject.getString("msg");
                    Log.i("结果",rs);

                    JSONArray jsonArray = jsonObject.getJSONArray("value");
                    //获取章节数
                    int number = jsonArray.length();
                    Log.i("公式数",number+"");
                    for(int i =0;i<number;i++){
                        JSONObject  jo = jsonArray.getJSONObject(i);
                        Log.i("第"+i+"条公式",jo.getString("formula"));
                        //mList.add(jo.getString("formula"));
                       sb.append("<font size:16px>"+String.valueOf(i+1)+"."+jo.getString("formula")+"</font>"+"</p></p>");
                       mHandler.sendEmptyMessage(0);
                    }

                    System.out.println("公式内容："+sb.toString());

                }catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("出错","error");
                }
            }
        }.start();
    }
    }
