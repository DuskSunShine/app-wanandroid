package com.scy.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public abstract class BaseFragment<P extends AbsPresenter> extends Fragment implements AbsView {
    protected View rootView;
    private P absPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        absPresenter=createPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(onCreateView(), container, false);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (absPresenter!=null) {
            absPresenter.attachView(this);
        }
        if (absPresenter!=null) {
            providePresenter(absPresenter);
        }
        beforeInitView();
        initView(rootView);
        initData();
    }


    protected abstract P createPresenter();

    protected abstract void providePresenter(P absPresenter);

    protected abstract int onCreateView();

    protected abstract void beforeInitView();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (absPresenter.isAttachedView()){
            absPresenter.detachView();
        }
    }

}
