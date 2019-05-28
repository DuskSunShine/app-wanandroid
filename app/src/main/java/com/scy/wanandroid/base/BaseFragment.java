package com.scy.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<P extends AbsPresenter>
        extends Fragment implements AbsView,View.OnClickListener {
    protected View rootView;
    protected P mPresenter;
    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        unBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }else {
            throw  new NullPointerException(getClass().getSimpleName()+"未创建Presenter,无法交互");
        }
        initDataAndEvents();
    }


    protected abstract P createPresenter();

    //protected abstract void providePresenter(P absPresenter);

    protected abstract int getLayoutId();

    protected abstract void initDataAndEvents();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null&&
                mPresenter.isAttachedView()){
            mPresenter.detachView();
            mPresenter=null;
        }
    }
    public void setOnClick(int... ids) {
        for (int id : ids)
           rootView.findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

}
