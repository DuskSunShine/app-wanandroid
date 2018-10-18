package com.scy.wanandroid.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by SCY on 2018/10/16 at 17:54.
 */
public interface AbsPresenter<V extends AbsView> {

    /**
     *建立和view关系
     * @param view
     */
    void  attachView(V view);

    /**
     * 销毁view
     */
    void detachView();

    /**
     * 是否关联view
     * @return true 关联
     */
    boolean isAttachedView();

    /**
     * 订阅关系建立
     * @param disposable {@link Disposable}
     */
    void addRxBindingSubscribe(Disposable disposable);

    /**
     * 取消订阅关系
     */
    void disposeSubscribe();
}
