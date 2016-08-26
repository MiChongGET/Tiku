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
                Toast.makeText(zhangjiexunlian.this,"back",Toast.LENGTH_SHORT).show();
            }
        });



        listView  = (ListView) findViewById(R.id.zhangjielianxi_listView);
        listView.setAdapter(new ArrayAdapter<String>(zhangjiexunlian.this,R.layout.list_item,data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(zhangjiexunlian.this,data[position],Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        Intent intent = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("capter","第一章");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("capter","第二章");
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("capter","第三章");
                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("capter","第四章");
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("capter","第五章");
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle5 = new Bundle();
                        bundle5.putString("capter","第六章");
                        intent5.putExtras(bundle5);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle6 = new Bundle();
                        bundle6.putString("capter","第七章");
                        intent6.putExtras(bundle6);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(zhangjiexunlian.this,ZuoTiBan.class);
                        Bundle bundle7 = new Bundle();
                        bundle7.putString("capter","第八章");
                        intent7.putExtras(bundle7);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }
}
