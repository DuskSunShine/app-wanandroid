package com.scy.wanandroid.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.scy.wanandroid.utils.AppManager;

import java.util.Objects;


public abstract class BaseActivity<P extends AbsPresenter>
        extends AppCompatActivity implements AbsView,View.OnClickListener {

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        AppManager.getInstance().addActivity(this);
        mPresenter=createPresenter();
        beforeInitView();
        initView();
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }else {
            throw  new NullPointerException(mPresenter.getClass().getSimpleName()+"未创建");
        }
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
    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    /**
     * Set the View to the return key
     */
    protected void setToBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * Set the View to the return key
     */
    protected void setToBack(int id) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}