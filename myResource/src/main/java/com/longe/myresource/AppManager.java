package com.longe.myresource;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;

import java.util.Stack;

/**
 * AppManager   管理activity，方便一次销毁
 *
 * @author ZGQ
 * @version 1.0, 2017/7/13 0013
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private PendingIntent restartIntent;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
            if (activityStack == null) {
                activityStack = new Stack<Activity>();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

//    /**
//     * 结束当前Activity（堆栈中最后一个压入的）
//     */
//    public void finishActivity() {
//        Activity activity = activityStack.lastElement();
//        if (activity != null)
//        finishActivity(activity);
//    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }
}
