package com.scy.wanandroid.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class AppManager {

    /**
     * Activity storage stacks
     */
    private static Stack<Activity> mActivityStack;

    /**
     * AppManager singleton object
     */
    private static AppManager mAppManager;

    private AppManager() {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
    }

    /**
     * Single Instance
     */
    public static AppManager getInstance() {
        if (mAppManager == null) {
            synchronized (AppManager.class) {
                if (mAppManager == null) {
                    mAppManager = new AppManager();
                }
            }
        }
        return mAppManager;
    }

    /**
     * Add Activity to storage stacks
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * Get to the top Activity
     */
    public Activity getTopActivity() {
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * The end of the stack top Activity
     */
    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * End specified Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * End the specified class name Activity
     */
    public synchronized void killActivity(Class<?>... calsses) {

        if (mActivityStack == null || mActivityStack.isEmpty())
            return;

        List<Activity> activities = new ArrayList<>();

        for (Class cls : calsses) {
            for (Activity activity : mActivityStack) {
                if (activity.getClass().equals(cls)) {
                    activities.add(activity);
                }
            }
        }

        for (Activity activity : activities) {
            killActivity(activity);
        }

    }

    /**
     * End all Activity
     */
    public void killAllActivity() {
        if (mActivityStack == null || mActivityStack.isEmpty()) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * End others Activity,Out of this
     */
    public void killOthersActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i) && activity != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
        mActivityStack.add(activity);
    }

    /**
     * this Activity if exist
     */
    public boolean existActivity(String className) {
        Activity activity = getActivityByName(className);
        if (activity != null && !activity.isFinishing()) {
            return true;
        }
        return false;
    }

    /**
     * Search by name Activity
     */
    public Activity getActivityByName(String className) {
        Activity activity = null;
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (mActivityStack.get(i).getClass().getName().equals(className)) {
                    activity = mActivityStack.get(i);
                }
            }
        }
        return activity;
    }

    /**
     * Close Activity
     */
    public void finishActivity(Activity activity) {

        int pos = -1;
        if (activity != null && mActivityStack != null) {
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i)) {
                    if (activity == mActivityStack.get(i)) {
                        pos = i;
                        activity.finish();
                    }
                }
            }
            if (pos != -1) {
                mActivityStack.remove(pos);
            }
        }
    }

    /**
     * Delete activity from the stack
     */
    public void remove(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * Exit application
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }

}
