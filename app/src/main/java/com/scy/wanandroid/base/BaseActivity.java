package com.scy.wanandroid.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scy.wanandroid.utils.AppManager;

import java.util.Objects;


public abstract class BaseActivity<P extends AbsPresenter> extends AppCompatActivity implements AbsView {

    private P absPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        AppManager.getInstance().addActivity(this);
        absPresenter=createPresenter();
        if (absPresenter!=null) {
            absPresenter.attachView(this);
        }
        if (absPresenter!=null) {
           providePresenter(absPresenter);
        }
        beforeInitView();
        initView();
        initData();

    }
    public abstract int getContentViewId();

    public abstract P createPresenter();

    public abstract void providePresenter(P absPresenter);


    public abstract void beforeInitView();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (absPresenter!=null&&
                absPresenter.isAttachedView()){
            absPresenter.detachView();
        }
    }
}