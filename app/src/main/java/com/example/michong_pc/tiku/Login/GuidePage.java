package com.example.michong_pc.tiku.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.michong_pc.tiku.Activity_home;
import com.example.michong_pc.tiku.R;

public class GuidePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide_page);
        startActivity();
    }

    private void startActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("name","null");
                if (name.equals("null")){
                    startActivity(new Intent(GuidePage.this,Login.class));
                    finish();
                }else{
                    startActivity(new Intent(GuidePage.this,Activity_home.class));
                    finish();
                }

            }
        },2000);
    }
}
