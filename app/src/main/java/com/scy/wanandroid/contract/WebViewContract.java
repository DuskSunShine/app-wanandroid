package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;

/**
 * Created by SCY on 2018/10/23 at 17:49.
 */
public interface WebViewContract {

    interface WebView extends AbsView{

    }

    interface Presenter extends AbsPresenter<WebView>{

    }
}
