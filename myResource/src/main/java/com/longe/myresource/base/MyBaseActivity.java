package com.longe.myresource.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * MyBaseActivity  activity 基类， 方面统一管理
 *
 * @author 左桂清
 * @version 1.0, 2016/10/26
 */
public class MyBaseActivity extends AppCompatActivity {

    /**
     * 用来保存所有Activity
     */
    private static ArrayList<MyBaseActivity> onlineActivityList = new ArrayList<>();

    /**
     * 一次退出当前所存在的Activity
     */
    public static void finishAll() {
        Iterator<MyBaseActivity> iterator = onlineActivityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }
    }

    //handler---------------------------------------------------------------------------------------
    //初始化mHandler,并重写handlerMessage方法,handlerMessage方法来处理消息
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //            super.handleMessage(msg);
            handleMainMessage(msg);
        }
    };

    //子类需要处理消息时可以重写此方法
    protected void handleMainMessage(Message msg) {

    }

    //普通跳转
    public void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    //带参数跳转
    public void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //带动画跳转
    public void startActivity(Class<?> targetClass, int inAnimID, int outAnimID) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }

    //带动画，带参数，跳转
    public void startActivity(Class<?> targetClass, int inAnimID, int outAnimID, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        overridePendingTransition(inAnimID, outAnimID);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //固定方法
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //全屏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        //透明状态栏
//        UtStatusBar.setTranslucent(this);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
