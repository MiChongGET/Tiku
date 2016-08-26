package com.example.michong_pc.tiku.function_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.Result.Result_score;

public class Test extends AppCompatActivity {

    LinearLayout l1;
    LinearLayout l2;
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
        l1 = (LinearLayout) findViewById(R.id.zhengti_exam);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test.this, Result_score.class));
            }
        });
    }
}
