package com.example.michong_pc.tiku.function_activity.Train_mode;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michong_pc.tiku.R;

public class train extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private String URL = "";
    private GridView gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("题型训练");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Toast.makeText(train.this,"BACK",Toast.LENGTH_SHORT).show();
            }
        });


        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Huawen.ttf");

        textView1 = (TextView) findViewById(R.id.caiyun1);
        textView2 = (TextView) findViewById(R.id.caiyun2);
        textView3 = (TextView) findViewById(R.id.caiyun3);
        textView4 = (TextView) findViewById(R.id.caiyun4);
        textView4.setTypeface(typeface);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        findViewById(R.id.xuanze_L).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(train.this,"填空题",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(train.this,Zuotiban_tiankongti.class);
                Bundle bundle = new Bundle();
                bundle.putString("capter","填空题");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        findViewById(R.id.jisuanti_L).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(train.this,"计算题",Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.zhengmingti_L).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(train.this,"证明题",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.zongheti_L).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(train.this,"综合题",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return true;
    }

}
