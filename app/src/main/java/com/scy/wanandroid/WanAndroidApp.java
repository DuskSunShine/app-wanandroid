package com.scy.wanandroid;

import android.app.Application;

import com.scy.wanandroid.constants.Constants;
import com.tencent.bugly.crashreport.CrashReport;

import me.logg.Logg;
import me.logg.config.LoggConfiguration;

/**
 * Created by SCY on 2018/10/18 at 15:17.
 */
public class WanAndroidApp extends Application {

    private static volatile WanAndroidApp wanAndroidApp=null;


    @Override
    public void onCreate() {
        super.onCreate();
        wanAndroidApp=this;
        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(true)
                .setTag("WanAndroid")// 自定义全局Tag
                .build();
        Logg.init(configuration);

        CrashReport.initCrashReport(this,Constants.BUGLY_APPID,false);
    }

    public static WanAndroidApp getWanAndroidApp() {
        return wanAndroidApp;
    }
}
