package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseSwipeActivity;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.contract.WebViewContract;
import com.scy.wanandroid.presenter.WebViewPresenter;


public class WebActivity extends BaseSwipeActivity<WebViewPresenter>
        implements WebViewContract.WebView {


    private FrameLayout webView;
    private String url="";
    private String title="";
    private AppCompatTextView main_title;
    private AgentWeb agentWeb;
    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    public WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        webView= (FrameLayout) findViewById(R.id.webView);
        main_title= (AppCompatTextView) findViewById(R.id.main_title);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initData() {
        agentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(webView, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .setMainFrameErrorView(R.layout.web_error,-1)
                .createAgentWeb()
                .ready()
                .go(url);
        mPresenter.settingWebView(agentWeb);
        main_title.setText(title);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showApiErrorMsg(String s) {

    }

    @Override
    public void showErrorCodeMsg(String s) {

    }



    @Override
    public void loadUrl() {
        Intent intent = getIntent();
        if (intent!=null){
            url=intent.getStringExtra(IntentKey.WEB_URL);
            title=intent.getStringExtra(IntentKey.TITLE);
        }
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return agentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
