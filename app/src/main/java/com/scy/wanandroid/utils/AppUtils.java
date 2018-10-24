package com.scy.wanandroid.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.ui.WebActivity;

/**
 * Created by SCY on 2018/10/19 at 14:32.
 */
public class AppUtils {

    /***
     * 非空判断
     * @param o
     * @return
     */
    public static boolean nonNull(Object o){
        return o!=null;
    }

    /**
     * 空判断
     * @param o
     * @return
     */
    public static boolean isNull(Object o){
        return o==null;
    }

    /**
     * 字符串不为空
     * @param s
     * @return
     */
    public static boolean nonEmpty(String s){
        return !TextUtils.isEmpty(s)&&!s.equals("");
    }

    /**
     *  jump to webActivity
     * @param activity
     * @param url
     */
    public static void goWebView(Activity activity,String url,String title){
        Intent intent=new Intent(activity,WebActivity.class);
        intent.putExtra(IntentKey.WEB_URL,url);
        intent.putExtra(IntentKey.TITLE,title);
        activity.startActivity(intent);
    }
}
