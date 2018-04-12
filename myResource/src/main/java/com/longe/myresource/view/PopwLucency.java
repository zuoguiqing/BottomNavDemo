package com.longe.myresource.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.longe.myresource.R;


/**
 * MyPopWindow
 *
 * @author ShiXiang
 * @version 1.0, 1/3/2017
 */

public class PopwLucency extends PopupWindow {
    private Activity activity;
    public View contentView;

    public PopwLucency(Activity activity, int resId, int with, int height) {
        this.activity = activity;
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

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);

        setBackgroundAlpha(0.5f);
    }

    @Override
    public void dismiss() {
        super.dismiss();

        setBackgroundAlpha(1f);
    }

    //设置背景透明度 0~1
    public void setBackgroundAlpha(float backgroundAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = backgroundAlpha;

        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//兼容华为
        window.setAttributes(layoutParams);
    }

    /*** 用例 ***************************************************/
    private PopwLucency photoDl;

    private void initPhotoDl() {
        photoDl = new PopwLucency(activity, R.layout.my_popw_photo,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        photoDl.setAnim(R.style.PopwBToTAnima);
        View view = photoDl.getContentView();

        TextView gallery = (TextView) view.findViewById(R.id.gallery);
        TextView camera = (TextView) view.findViewById(R.id.camera);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDl.dismiss();


            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDl.dismiss();


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDl.dismiss();
            }
        });
    }

    private void showPhotoDl() {
        if (photoDl == null) {
            initPhotoDl();
        }
//        photoDl.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }
}
