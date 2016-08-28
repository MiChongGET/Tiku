package com.example.michong_pc.tiku.function_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.Result.Result_score;
import com.example.michong_pc.tiku.adapter.BaseAdapter;
import com.example.michong_pc.tiku.bean.GridBean;
import com.example.michong_pc.tiku.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    private ListView lv ;
    List<TestBean> beanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("考试模式");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Toast.makeText(Test.this,"BACK",Toast.LENGTH_SHORT).show();
            }
        });

        /*l1 = (LinearLayout) findViewById(R.id.zhengti_exam);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test.this, Result_score.class));
            }
        });*/

        lv = (ListView) findViewById(R.id.test_listView);
        beanList = new ArrayList<>();
        int [] icons  = {R.drawable.true_exam,R.drawable.suiji_exam};
        for (int i = 0; i<icons.length;i++){
            TestBean bean = new TestBean(icons[i]);
            beanList.add(bean);
        }
        BaseAdapter baseAdapter = new BaseAdapter(this,beanList);
        lv.setAdapter(baseAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(Test.this,Result_score.class));
                        break;
                    case 1:
                        startActivity(new Intent(Test.this,Result_score.class));
                }
            }
        });
    }
}
