package com.scy.wanandroid.base;

/**
 * Created by SCY on 2018/10/16 at 17:54.
 */
public interface AbsView {
    /**
     * api异常信息
     * @param s
     */
    void showApiErrorMsg(String s);

    /**
     * 返回errorCode错误信息
     * @param s 返回的errorMsg
     */
    void showErrorCodeMsg(String s);
}
