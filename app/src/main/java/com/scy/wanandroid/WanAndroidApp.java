package com.scy.wanandroid;

import android.app.Application;

/**
 * Created by SCY on 2018/10/18 at 15:17.
 */
public class WanAndroidApp extends Application {

    private static volatile WanAndroidApp wanAndroidApp=null;


    @Override
    public void onCreate() {
        super.onCreate();
        wanAndroidApp=this;
    }

    public static WanAndroidApp getWanAndroidApp() {
        return wanAndroidApp;
    }
}
