package com.scy.wanandroid.presenter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.just.agentweb.AgentWeb;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.WebViewContract;

/**
 * Created by SCY on 2018/10/23 at 17:50.
 */
public class WebViewPresenter extends BasePresenter<WebViewContract.WebView>
implements WebViewContract.Presenter{
    @Override
    protected void startInteractive() {
        mView.loadUrl();
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void settingWebView(AgentWeb agentWeb) {
        WebView mWebView = agentWeb.getWebCreator().getWebView();
        WebSettings webSetting = mWebView.getSettings();
//        mSettings.setAppCacheEnabled(false);
//        mSettings.setDomStorageEnabled(false);
//        mSettings.setDatabaseEnabled(false);
//        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        mSettings.setJavaScriptEnabled(true);
//        mSettings.setSupportZoom(true);
//        mSettings.setBuiltInZoomControls(true);
//        //不显示缩放按钮
//        mSettings.setDisplayZoomControls(false);
//        //设置自适应屏幕，两者合用
//        //将图片调整到适合WebView的大小
//        mSettings.setUseWideViewPort(true);
//        //缩放至屏幕的大小
//        mSettings.setLoadWithOverviewMode(true);
//        //自适应屏幕
//        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        //webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        //webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
    }
}
