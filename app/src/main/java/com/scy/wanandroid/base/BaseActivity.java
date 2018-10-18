package com.scy.wanandroid.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scy.wanandroid.utils.AppManager;

import java.util.Objects;


public abstract class BaseActivity<P extends AbsPresenter> extends AppCompatActivity implements AbsView {

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        AppManager.getInstance().addActivity(this);
        mPresenter=createPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }else {
           throw  new NullPointerException(mPresenter.getClass().getSimpleName()+"未创建");
        }
        beforeInitView();
        initView();
        initData();

    }
    public abstract int getContentViewId();

    public abstract P createPresenter();

    //public abstract void providePresenter(P absPresenter);


    public abstract void beforeInitView();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
        if (mPresenter!=null&&
                mPresenter.isAttachedView()){
            mPresenter.detachView();
        }
    }
}