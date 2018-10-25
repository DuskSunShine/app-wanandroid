package com.scy.wanandroid.contract;

import com.just.agentweb.AgentWeb;
import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;

/**
 * Created by SCY on 2018/10/23 at 17:49.
 */
public interface WebViewContract {

    interface WebView extends AbsView{
        void loadUrl();
    }

    interface Presenter extends AbsPresenter<WebView>{
        void settingWebView(AgentWeb agentWeb);
    }
}
