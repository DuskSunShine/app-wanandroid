package com.scy.wanandroid.utils;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;

/**
 * Created by SCY on 2018/10/23 at 14:10.
 */
public class WanAndroidDialog {

    /**
     * 保证在UI线程中显示最新的一次
     */

    private WanAndroidDialog(){}

    private static WanAndroidDialog wanAndroidDialog=null;
    private static AlertDialog dialog;
    public static WanAndroidDialog create(){

        if (wanAndroidDialog==null){
               synchronized (WanAndroidDialog.class){
                   wanAndroidDialog=new WanAndroidDialog();
               }
        }
        return wanAndroidDialog;

    }

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0) {
                if (dialog != null) {
                    dialog.cancel();
                }
               String text= (String) msg.obj;
                dialog=new AlertDialog.Builder(AppManager.getInstance().getTopActivity())
                        .setMessage(text)
                        .create();
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        (dialogInterface, i) -> dialogInterface.dismiss());
                dialog.show();
            }
        }
    };

    public static void dialog(String text) {
        mHandler.sendMessage(mHandler.obtainMessage(0,text));

    }

}
