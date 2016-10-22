package com.example.michong_pc.tiku.function_activity.Chapter_mode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michong_pc.tiku.JSON.HttpUtils;
import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.ViewFlipper.MyViewFlipper;
import com.example.michong_pc.tiku.drawlibrary.DrawerLayout;
import com.example.michong_pc.tiku.function_activity.choose_question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.kexanie.library.MathView;

import static com.example.michong_pc.tiku.JSON.JSONError.removeBOM;

public class ZuoTiBan extends AppCompatActivity implements MyViewFlipper.OnViewFlipperListener {
    private MyViewFlipper myViewFlipper;
    private int currentNumber;
    private int question_num = 0;
    private TextView page;
    private TextView page_total;
    private int total = 11;
    private DrawerLayout mDrawerLayout;


    private MathView mathView2;
    private String TAG = "哈哈哈";

    private String result = "";
    private Button choose;
    private String ID;
    private String chapter_url;
    private String[][] question_content;
    private ImageButton sign;
    private int flag = 0;

    private ProgressBar mProgressBar;
    private LinearLayout edit_input;
    private Button prev;
    private Button next;


    //处理接收的网络数据
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //设置做题进度
            mProgressBar.setMax(question_num);
            mProgressBar.setProgress(currentNumber);
            //在题板上添加问题
            mathView2 = (MathView) findViewById(R.id.question_ban);
            //这样写可以解决webview的异常,主要是多个webview不在同一个线程

            mathView2.setText(question_content[currentNumber - 1][0]);
            page_total.setText(String.valueOf(question_num));

            sign.setImageDrawable(getResources().getDrawable(R.drawable.no_start));
            //flag标记符清除
            flag = 0;
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag == 0) {
                        //标记题目
                        ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.start));
                        Toast.makeText(ZuoTiBan.this, "标记题目", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    } else {
                        //取消标记题目
                        ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.no_start));
                        Toast.makeText(ZuoTiBan.this, "取消标记题目", Toast.LENGTH_SHORT).show();
                        flag = 0;
                    }
                }
            });
            Log.i("题号。。。", "handleMessage: " + currentNumber);
//            if(currentNumber<5) {
//                getFragmentManager().beginTransaction().replace(R.id.ban,choose_fragment).commit();
//            }else{
//                getFragmentManager().beginTransaction().replace(R.id.ban, zm_fragment).commit();
//            }


            Panduan();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ti_ban);
        //传递第几套的数值
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ID = b.getString("id");
        chapter_url = "http://tk.e8net.cn/ApiLibrary/getCatalogLibrary/id/"+ID;

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar2);
        toolbar.setTitle(b.getString("capter"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //标记题目
        sign = (ImageButton) findViewById(R.id.sign);
//        mSharedPreferences = getSharedPreferences("question_id", Context.MODE_PRIVATE);
//        mEditor = mSharedPreferences.edit();
//        int num = mSharedPreferences.getInt("id",0);
//
//        //题号，默认是第一题
//        if(num==0) {
//            currentNumber = 1;
//        }else{currentNumber=num;}
        currentNumber=1;
        myViewFlipper = (MyViewFlipper) findViewById(R.id.body_flipper);
        myViewFlipper.setOnViewFlipperListener(this);
        myViewFlipper.addView(createView(currentNumber));

        page_total = (TextView) findViewById(R.id.page_total);
        page_total.setText(String.valueOf(total));


        //做题进度条使用
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        //答案输入的功能设置
        edit_input = (LinearLayout) findViewById(R.id.edit_input);


        //上一题下一题
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.dial_drawer);

        //面板弹起
        mDrawerLayout.setInitialState(DrawerLayout.State.Close); //set drawer initial state: open or close
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void drawerOpened() {
                Log.i(TAG, "drawerOpened: "+"面板弹起");
            }

            @Override
            public void drawerClosed() {
                Log.i(TAG, "drawerOpened: "+"面板关闭");
            }
        });



        //选择题目
        choose = (Button) findViewById(R.id.choose_question);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZuoTiBan.this,choose_question.class);
                Bundle bundle = new Bundle();
                bundle.putInt("question_id",question_num);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });
    }

    public View createView(final int currentNumber) {

        //创建一个线程，里面包含了JSON数据解析
        new Thread() {
            @Override
            public void run() {
                //JSON数据解析

                try {

                    result = HttpUtils.get(chapter_url);
                    Log.i("测试",result);
                    String NewResult  =removeBOM(result);
                    Log.i("返回结果", NewResult);
                    JSONObject jsonObject = new JSONObject(NewResult);
                    String error_code = jsonObject.getString("result");
                    Log.i("调试结果", error_code);


                    JSONArray jsonArray = jsonObject.getJSONArray("value");

                    //获取题目的数目
                     question_num=jsonArray.length();
                     Log.i("题目数","共"+question_num+"个题目");
                    question_content = new String[question_num][2];


                    for(int i =0;i<question_num;i++){
                        JSONObject  jo = jsonArray.getJSONObject(i);
                        Log.i("第"+i+"个题目",jo.getString("content"));
                        //mList.add(jo.getString("formula"));
                        question_content[i][0] = String.valueOf(i+1)+".  "+jo.getString("content");
                        question_content[i][1] = jo.getString("id");
                    }

                    for(int i = 0;i<currentNumber;i++) {
                        System.out.println("题目的ID" + question_content[i][1]);
                    }
                    mHandler.sendEmptyMessage(0);
                    System.out.println("此页面"+currentNumber);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        ScrollView resultView = (ScrollView) layoutInflater.inflate(R.layout.flipper_view, null);
        page = (TextView) findViewById(R.id.page);
        page.setText(String.valueOf(currentNumber));
        return resultView;

    }

    //获取下一个页面
    @Override
    public View getNextView() {
        //判断题目是否做完
        if(currentNumber==question_num ){
            new AlertDialog.Builder(this)
                    .setTitle("所有题目已经做完，是否退出？")
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ZuoTiBan.this.finish();
                        }
                    })
                    .setNeutralButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setCancelable(false)
                    .show();
        }

        currentNumber = currentNumber == question_num ? 1 : currentNumber + 1;

        return createView(currentNumber);
    }

    //获取上一个页面
    @Override
    public View getPreviousView() {

        currentNumber = currentNumber == 1 ? question_num : currentNumber - 1;
        return createView(currentNumber);
    }
//
//    //按钮控制得到上一个页面
//    public void prev(View source) {
//        myViewFlipper.flingToPrevious();
//        myViewFlipper.stopFlipping();
//
//    }
//
//    //按钮获取下一个页面
//    public void next(View source) {
//        myViewFlipper.flingToNext();
//        myViewFlipper.stopFlipping();
//
//    }



    public void Panduan(){
        //设置跳转上一题下一题的功能
        if (currentNumber > 1) {
            //设置上一题按钮不可点击
            prev.setEnabled(true);
        }
        //当题目为1时不可以点击
        if (currentNumber == 1) {
            prev.setEnabled(false);
            Log.i(TAG, "onClick: " + currentNumber);
        }

        if (currentNumber < 5 && currentNumber > 0) {
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myViewFlipper.flingToPrevious();
                    myViewFlipper.stopFlipping();

                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewFlipper.flingToNext();
                    myViewFlipper.stopFlipping();
                }
            });
        }
        if (currentNumber >= 5 && currentNumber < question_num) {
            //输入面板收起
            edit_input.setVisibility(View.GONE);

            //判断会不会
            prev.setText("会");
            //当会时，弹出面板输入分数
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //面板打开
                    mDrawerLayout.openDrawer();
                }
            });

            //不会时，跳到下一题
            next.setText("不会");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewFlipper.flingToNext();
                    myViewFlipper.stopFlipping();
                }
            });

        }
        if (currentNumber == question_num) {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("确定所有的题目已经做完,确定退出？")
                .setIcon(android.R.drawable.ic_menu_save)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ZuoTiBan.this.finish();
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case 0:
                Bundle bundle = data.getExtras();
                currentNumber = bundle.getInt("tihao");
                mHandler.sendEmptyMessage(0);
                page.setText(String.valueOf(currentNumber));

                //防止跳转之后出现填空和证明题答题界面的Bug
                prev.setText("上一题");
                next.setText("下一题");
                edit_input.setVisibility(View.VISIBLE);
//
//                //
//                //设置跳转上一题下一题的功能
//                if (currentNumber>1) {
//                    Log.i(TAG, "handleMessage: 有反应");
//                    //设置上一题按钮不可点击
//                    prev.setEnabled(true);
//                    next.setEnabled(true);
//                }
//                //当题目为1时不可以点击
//                if(currentNumber==1){
//                    prev.setEnabled(false);
//                    next.setEnabled(true);
//                    Log.i(TAG, "onClick: "+currentNumber);
//                }
//                //所有的题目已经做完
//                if (currentNumber==question_num){
//                    Toast.makeText(ZuoTiBan.this,"所有的题目已经做完！！！",Toast.LENGTH_LONG).show();
//
//                }
                Panduan();
                break;

            case 1:
                break;
            default:
                break;
        }

    }
}

