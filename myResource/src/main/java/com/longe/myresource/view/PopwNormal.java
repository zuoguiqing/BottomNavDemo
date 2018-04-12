package com.longe.myresource.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * MyPopWindow
 *
 * @author ShiXiang
 * @version 1.0, 1/3/2017
 */

public class PopwNormal extends PopupWindow {
    private Activity mActivity;
    public View contentView;

    public PopwNormal(Activity activity, int resId, int with, int height) {
        this.mActivity = activity;
        this.contentView = LayoutInflater.from(activity.getBaseContext()).inflate(resId, null);

        // 设置视图
        this.setContentView(this.contentView);
        // 设置弹出窗体的宽和高
        this.setWidth(with);
        this.setHeight(height);

        // 设置焦点和外部取消
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));

        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    // 设置动画
    public void setAnim(int animId) {
        this.setAnimationStyle(animId);
    }

//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        super.showAtLocation(parent, gravity, x, y);
//
//        setBackgroundAlpha(0.5f);
//    }
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//
//        setBackgroundAlpha(1f);
//    }

    //设置背景透明度 0~1
//    public void setBackgroundAlpha(float backgroundAlpha) {
//        WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
//        layoutParams.alpha = backgroundAlpha;
//        mActivity.getWindow().setAttributes(layoutParams);
//    }

}
