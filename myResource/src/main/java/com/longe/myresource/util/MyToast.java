package com.longe.myresource.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author ShiXiang
 * @version 1.0, 2016/11/23
 */
public class MyToast {
    private static Context con;
    private static Toast toast;

    public static void init(Context context) {
        con = context;
        getInstance();
    }

    private static void getInstance() {
        if (toast == null)
            toast = new Toast(con);
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        getInstance();
        toast.cancel();
        toast.makeText(con, message, duration).show();
    }
}
