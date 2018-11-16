package com.scy.wanandroid.base;

import android.text.TextUtils;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;

import io.reactivex.observers.ResourceObserver;
import me.logg.Logg;
import retrofit2.HttpException;

/**
 *
 * @param <T>
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private AbsView mView;
    private String mErrorMsg;


    protected BaseObserver(AbsView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(AbsView view, int resId){
        this.mView = view;
        this.mErrorMsg = WanAndroidApp.getWanAndroidApp().getString(resId);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Logg.e(e);
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showApiErrorMsg(mErrorMsg);
        }else if (e instanceof HttpException) {
                mView.showApiErrorMsg(WanAndroidApp.getWanAndroidApp().getString(R.string.http_error));
            //mView.showApiErrorMsg(e.getMessage());
        } else {
            mView.showApiErrorMsg(WanAndroidApp.getWanAndroidApp().getString(R.string.unKnown_error));
        }
    }
}
