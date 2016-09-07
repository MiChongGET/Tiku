package com.example.michong_pc.tiku.function_activity.Test_mode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.michong_pc.tiku.Fragment_study;
import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.Result.Result_score;

public class Test extends AppCompatActivity {
    //考试模式代码
    private ListView lv ;
    ImageView imageView;
    RelativeLayout r1;
    RelativeLayout r2;
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
                //startActivity(new Intent(Test.this, Fragment_study.class));
                finish();
            }
        });



        imageView = (ImageView) findViewById(R.id.image_test);
        r1 = (RelativeLayout) findViewById(R.id.r1);
        r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test.this, Test_chooce.class));
            }
        });
        r2 = (RelativeLayout) findViewById(R.id.r2);
        r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test.this, Result_score.class));
            }
        });

    }
    //监听返回键

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            //startActivity(new Intent(Test.this, Fragment_study.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
