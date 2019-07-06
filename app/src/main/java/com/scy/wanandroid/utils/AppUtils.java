package com.scy.wanandroid.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.ui.WebActivity;
import com.scy.wanandroid.ui.X5WebActivity;

import java.util.Random;

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
        Intent intent=new Intent(activity, X5WebActivity.class);
        intent.putExtra(IntentKey.WEB_URL,url);
        intent.putExtra(IntentKey.TITLE,title);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.right_in,R.anim.right_in_show);
    }

    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

    public static int dp2px(float dpValue) {
        final float scale = WanAndroidApp.getWanAndroidApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
