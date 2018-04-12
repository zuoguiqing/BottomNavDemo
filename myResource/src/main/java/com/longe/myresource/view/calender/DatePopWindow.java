package com.longe.myresource.view.calender;


import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.longe.myresource.R;


/**
 * DatePopWindow  封装日历弹窗
 * @author 左桂清
 * @version 1.0, 2017/2/24 0024
 * @since
 */
public class DatePopWindow {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private PopupWindow pop;

    ImageView iv_month_last,iv_month_next,iv_year_next,iv_year_last,ivClose;
    TextView tv_year,tv_month;
    MonthDateView dateView;
    Button btn_confirm;
    String dates;

    public DatePopWindow(Activity activity) {
        this.activity = activity;

        layoutInflater = LayoutInflater.from(activity);
        View layout = layoutInflater.inflate(R.layout.air_calendar_dl_date, null);

        dateView = (MonthDateView) layout.findViewById(R.id.monthDateView);
        btn_confirm = (Button) layout.findViewById(R.id.btn_confirm);
//        ivClose = (ImageView) layout.findViewById(R.id.iv_close);
        iv_month_last = (ImageView) layout.findViewById(R.id.iv_month_last);
        iv_month_next = (ImageView) layout.findViewById(R.id.iv_month_next);
        iv_year_next = (ImageView) layout.findViewById(R.id.iv_year_next);
        iv_year_last = (ImageView) layout.findViewById(R.id.iv_year_last);
        tv_year = (TextView) layout.findViewById(R.id.tv_year);
        tv_month = (TextView) layout.findViewById(R.id.tv_month);

        dateView.setmDaySize(13);
        dateView.setmNormalTextColor(activity.getResources().getColor(R.color.black_32));
        dateView.setmSelectBGColor(activity.getResources().getColor(R.color.blue_28));
        dateView.setmSelectedTextColor(activity.getResources().getColor(R.color.white));

        dateView.setTextView(tv_year, tv_month);
        pop = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击 back 键的时候，窗口会自动消失
        pop.setBackgroundDrawable(new BitmapDrawable());

        pop.setTouchable(true); // 设置popupWindow可点击
        pop.setOutsideTouchable(true); // 设置popupWindow外部可点击
        pop.setFocusable(true); // 获取焦点
        pop.setOnDismissListener(new PopupDismissListener());

        pop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //如果点击了popupWindow的外部，popupWindow也会消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    pop.dismiss();
                    return true;
                }
                return false;
            }
        });


//        ivClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//            }
//        });

        iv_month_last.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateView.onLeftClick();
            }
        });

        iv_month_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateView.onRightClick();
            }
        });

        iv_year_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateView.onCutYearClick();
            }
        });

        iv_year_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateView.onAddYearClick();
            }
        });
    }

    /**
     * 监听确认按钮点击事件
     */
    public void setOnClickListener(View.OnClickListener listener) {
        btn_confirm.setOnClickListener(listener);
    }

    /**
     * 获取选择内容
     */
    public String getDateContent() {
        int year = dateView.getmSelYear();
        int month = dateView.getmSelMonth() + 1;
        int day = dateView.getmSelDay();
        dates = year + "-" + month + "-" + day;
        return dates;
    }

    public void dismiss() {
        pop.dismiss();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 透明度
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    /**
     * @param isBackground 是否显示半透明背景
     * @param paramView    点击的按钮
     */
    public void show(View paramView, boolean isBackground) {

        if (isBackground) {
            backgroundAlpha(0.7f);
        }

        //设置窗口显示位置, 后面两个0 是表示偏移量，可以自由设置
        pop.showAtLocation(paramView, Gravity.CENTER, 0, 0);
        //更新窗口状态
        pop.update();
    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class PopupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

}
