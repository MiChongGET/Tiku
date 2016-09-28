package com.example.michong_pc.tiku.function_activity.Train_mode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.ViewFlipper.MyViewFlipper;
import com.example.michong_pc.tiku.drawlibrary.DrawerLayout;
import com.example.michong_pc.tiku.function_activity.choose_question;

public class Zuotiban_tiankongti extends AppCompatActivity implements MyViewFlipper.OnViewFlipperListener {
    private MyViewFlipper myViewFlipper;
    private int currentNumber;
    private TextView page;
    private TextView page_total;
    private int total=11;
    private EditText editText;

    private DrawerLayout mDrawerLayout;
    private LinearLayout mNumberLayout;
    private LinearLayout mDrawerContent;
    private int mTranslationY = 0;
    private boolean isOpened = false;
    private boolean isClosed = false;
    private Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ti_ban_tiankong);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar2);
        Intent intent = getIntent();
        Bundle b= intent.getExtras();
        toolbar.setTitle(b.getString("capter"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        choose = (Button) findViewById(R.id.choose_question);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Zuotiban_tiankongti.this, choose_question.class));
            }
        });

        currentNumber =1;
        myViewFlipper = (MyViewFlipper) findViewById(R.id.body_flipper);
        myViewFlipper.setOnViewFlipperListener(this);
        myViewFlipper.addView(createView(currentNumber));

        page_total = (TextView) findViewById(R.id.page_total);
        page_total.setText(String.valueOf(total));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.dial_drawer);
        mDrawerLayout.setInitialState(DrawerLayout.State.Close); //set drawer initial state: open or close
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void drawerOpened()
            {}
            @Override
            public void drawerClosed()
            {}
        });
    }

    private View createView(int currentNumber){

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        ScrollView resultView =(ScrollView) layoutInflater.inflate(R.layout.flipper_view,null);
        page= (TextView) findViewById(R.id.page);
        page.setText(String.valueOf(currentNumber));
        return resultView;

    }

    @Override
    public View getNextView() {
        currentNumber  = currentNumber ==total ? 1:currentNumber +1;
        return createView(currentNumber);
    }

    @Override
    public View getPreviousView() {

        currentNumber = currentNumber ==1 ? total:currentNumber-1;
        return createView(currentNumber);
    }

    public void prev(View source){
        myViewFlipper.flingToPrevious();
        myViewFlipper.stopFlipping();
    }

    public void next(View source){
        myViewFlipper.flingToNext();
        myViewFlipper.stopFlipping();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("确定退出？")
                .setIcon(android.R.drawable.ic_menu_save)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Zuotiban_tiankongti.this.finish();
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

