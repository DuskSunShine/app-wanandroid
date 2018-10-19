package com.scy.wanandroid.utils;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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


}
