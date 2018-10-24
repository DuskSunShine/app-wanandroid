package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseActivity;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.contract.WebViewContract;
import com.scy.wanandroid.presenter.WebViewPresenter;

public class WebActivity extends BaseActivity<WebViewPresenter>
        implements WebViewContract.WebView {


    private WebView webView;
    private ProgressBar progressBar;
    private String url="";
    private String title="";
    private AppCompatTextView main_title;
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
        Intent intent = getIntent();
        if (intent!=null){
            url=intent.getStringExtra(IntentKey.WEB_URL);
            title=intent.getStringExtra(IntentKey.TITLE);
        }
    }

    @Override
    public void initView() {
        webView=findViewById(R.id.webView);
        progressBar=findViewById(R.id.progressBar);
        main_title=findViewById(R.id.main_title);
        main_title.setText(title);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initData() {
        WebSettings mSettings = webView.getSettings();
        mSettings.setAppCacheEnabled(true);
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);

            }
        });
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            progressBar.setVisibility(View.INVISIBLE);
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
