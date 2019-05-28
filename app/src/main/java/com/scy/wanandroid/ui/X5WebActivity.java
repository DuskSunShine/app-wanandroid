package com.scy.wanandroid.ui;


import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseSwipeActivity;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.contract.WebViewContract;
import com.scy.wanandroid.presenter.WebViewPresenter;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import me.logg.Logg;

public class X5WebActivity extends BaseSwipeActivity<WebViewPresenter>
        implements WebViewContract.WebView {

    @BindView(R.id.webView)
    WebView webView;
    private String url = "";
    private String title = "";
    @BindView(R.id.main_title)
    AppCompatTextView main_title;
    @Override
    public int getContentViewId() {
        return R.layout.activity_x5_web;
    }

    @Override
    public WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }

    @Override
    public void initDataAndEvents() {
        main_title.setText(title);
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadUrl() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(IntentKey.WEB_URL);
            title = intent.getStringExtra(IntentKey.TITLE);
            Logg.i("WebActivity url   "+url);
        }
    }

    @Override
    public void showApiErrorMsg(String s) {

    }

    @Override
    public void showErrorCodeMsg(String s) {

    }
}
