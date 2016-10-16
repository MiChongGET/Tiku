package com.example.michong_pc.tiku.function_activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.michong_pc.tiku.bean.ScoreBean;
import com.example.michong_pc.tiku.function_activity.Chapter_mode.ZuoTiBan;
import com.example.michong_pc.tiku.function_activity.Test_mode.Test;
import com.example.michong_pc.tiku.function_activity.Test_mode.Test_chooce;

import java.util.ArrayList;
import java.util.List;

public class choose_question extends AppCompatActivity{
    private ScoreAdapter scoreAdapter;
    private GridView gridView;
    private TextView keep_time;
    private int id;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("question_id");
        System.out.println("题号"+id);

        mSharedPreferences = getSharedPreferences("question_id", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        iniView();
    }


    private void iniView() {
        List<ScoreBean> list  = new ArrayList<>();
        for(int j=1;j<=id;j++){
            ScoreBean bean = new ScoreBean();
            bean.setNum(String.valueOf(j)+".");
            list.add(bean);
        }


        gridView = (GridView) findViewById(R.id.gridView2);
        scoreAdapter = new ScoreAdapter(this,list);
        gridView.setAdapter(scoreAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num_tihao=position+1;
                Toast.makeText(choose_question.this,"跳转到"+num_tihao+"题",Toast.LENGTH_SHORT).show();
//                mEditor.putInt("id",num_tihao);
//                mEditor.commit();
                Intent intent = new Intent(choose_question.this, ZuoTiBan.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tihao",num_tihao);
                intent.putExtras(bundle);
                setResult(0,intent);
                finish();
            }
        });
    }


    @Override
    public void  onBackPressed() {
        Intent intent = new Intent(choose_question.this, ZuoTiBan.class);
        setResult(1,intent);
        finish();
    }
}
