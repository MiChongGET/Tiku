package com.example.michong_pc.tiku.Jiaowuchu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.michong_pc.tiku.R;

public class WebView extends AppCompatActivity {

    private String url = "http://211.70.176.123/wap/";
    private android.webkit.WebView mWebView;
    private Button exit_web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (android.webkit.WebView) findViewById(R.id.web_view);

        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
                return true;
            }
        });

        exit_web = (Button) findViewById(R.id.exit_web);
        exit_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(mWebView.canGoBack());
            {
                mWebView.goBack();//返回上一页
                return true;
            }
        }

        return super.onKeyDown(keyCode,event);
    }
}
