package com.example.michong_pc.tiku.function_activity.Test_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.function_activity.ZuoTiBan;

public class Test_chooce extends AppCompatActivity {

    private ListView listView;
    private final String title[]=new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chooce);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("真题训练");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test_chooce.this, Test.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });


        for(int i=0;i<title.length;i++){
           title[i] = String.valueOf(i+1)+".淮南师范学院2016-2017学年度第一学期试卷A(闭卷)";

       }

        listView = (ListView) findViewById(R.id.test_choose);
        listView.setAdapter(new ArrayAdapter<String>(Test_chooce.this,R.layout.list_item,title));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Test_chooce.this,Test_chooseBan.class);
                Bundle bundle = new Bundle();
                //设置标题为第几章
                bundle.putString("capter","第  "+String.valueOf(position+1)+"  套");
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


    }
    //监听返回键

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...

            Intent intent = new Intent(Test_chooce.this, Test.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
