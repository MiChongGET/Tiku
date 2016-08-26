package com.example.michong_pc.tiku.Result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.adapter.ScoreAdapter;

public class Result_score extends AppCompatActivity{
    private ScoreAdapter scoreAdapter;
    private GridView gridView;


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
                Toast.makeText(Result_score.this,"跳转到"+num_tihao+"题",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
