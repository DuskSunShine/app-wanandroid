package com.scy.wanandroid;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.scy.wanandroid.constants.Constants;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

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
        initLog();
        initX5();
        CrashReport.initCrashReport(this,Constants.BUGLY_APPID,false);
    }

    private void initX5() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Logg.i("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(wanAndroidApp,  cb);
    }

    private void initLog() {
        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(true)
                .setTag("WanAndroid")// 自定义全局Tag
                .build();
        Logg.init(configuration);
    }

    public static WanAndroidApp getWanAndroidApp() {
        return wanAndroidApp;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
