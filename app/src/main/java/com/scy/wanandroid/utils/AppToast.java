package com.scy.wanandroid.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;


/**
 * Created by SCY on 2018/9/28 at 10:12.
 */
public class AppToast {


    /**
     * 保证在UI线程中显示最新的一次Toast
     */
    private static Toast mToast = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            if (mToast != null) {
                mToast.cancel();
            }
            String text = (String) msg.obj;
            mToast = Toast.makeText(WanAndroidApp.getWanAndroidApp(), text, Toast.LENGTH_SHORT);
            mToast.show();
        }
    };

    public static void toast(String text) {
        mHandler.sendMessage(mHandler.obtainMessage(0, 0, Toast.LENGTH_SHORT, text));

    }

    public static void toast(int resId) {
        mHandler.sendMessage(mHandler.obtainMessage(0, 0, Toast.LENGTH_SHORT, WanAndroidApp.getWanAndroidApp().getString(resId)));

    }
}


