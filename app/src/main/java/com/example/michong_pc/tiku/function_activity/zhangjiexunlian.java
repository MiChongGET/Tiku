package com.example.michong_pc.tiku.function_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.michong_pc.tiku.R;

public class zhangjiexunlian extends AppCompatActivity {
    private ListView listView;
    private final String[] data = {"第一章  气体","第二章  热力学第一定律","第三章  热力学第二定律","第四章  多组分系统热力学及其在溶液中的应用","第五章  相平衡","第六章  化学平衡","第七章  统计热力学","第八章  电解质溶液"};
    private String[] number={"一","二","三","四","五","六","七","八"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhangjielianxi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle("章节练习");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        listView  = (ListView) findViewById(R.id.zhangjielianxi_listView);
        listView.setAdapter(new ArrayAdapter<String>(zhangjiexunlian.this,R.layout.list_item,data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                Bundle bundle = new Bundle();
                //设置标题为第几章
                bundle.putString("capter","第"+number[position]+"章");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
