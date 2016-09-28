package com.example.michong_pc.tiku.function_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.adapter.ScoreAdapter;
import com.example.michong_pc.tiku.function_activity.Test_mode.Test;
import com.example.michong_pc.tiku.function_activity.Test_mode.Test_chooce;

public class choose_question extends AppCompatActivity{
    private ScoreAdapter scoreAdapter;
    private GridView gridView;
    private TextView keep_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_question2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("题目选择");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        iniView();
    }

    private void iniView() {
        gridView = (GridView) findViewById(R.id.gridView2);
        scoreAdapter = new ScoreAdapter(this);
        gridView.setAdapter(scoreAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num_tihao=position+1;
                Toast.makeText(choose_question.this,"跳转到"+num_tihao+"题",Toast.LENGTH_SHORT).show();
            }
        });
    }


//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("确定退出？")
//                .setIcon(android.R.drawable.ic_menu_save)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent();
//                        intent.setClass(choose_question.this,Test.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .show();
//    }
}
