package com.scy.wanandroid.ui;



import android.content.Intent;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseActivity;
import com.scy.wanandroid.contract.SplashContract;
import com.scy.wanandroid.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.SplashView {


    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashPresenter createPresenter() {
        return null;
    }

    @Override
    public void providePresenter(SplashPresenter absPresenter) {

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
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
