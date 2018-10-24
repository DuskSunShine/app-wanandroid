package com.scy.wanandroid.ui;



import android.content.Intent;
import android.view.View;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseActivity;
import com.scy.wanandroid.contract.SplashContract;
import com.scy.wanandroid.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashPresenter>
        implements SplashContract.SplashView {


    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter();
    }


    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this,HomeActivity.class));
        finish();
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
}
