package com.example.michong_pc.tiku.Result;

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

public class Result_score extends AppCompatActivity{
    private ScoreAdapter scoreAdapter;
    private GridView gridView;
    private TextView keep_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("考试评分");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Result_score.this,Test_chooce.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        //显示考试所用时间
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        keep_time = (TextView) findViewById(R.id.keep_time);
        keep_time.setText(b.getString("keep_time"));

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
                Toast.makeText(Result_score.this,"跳转到"+num_tihao+"题",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("确定退出？")
                .setIcon(android.R.drawable.ic_menu_save)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(Result_score.this,Test.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
