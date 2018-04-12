package com.longe.myresource.view.calender;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.longe.myresource.R;


/**
 * DateAlertDialog  日历的dialog形式弹窗
 *
 * @author 左桂清
 * @version 1.0, 2017/3/8 0008
 */
public class DateAlertDialog {

    private AlertDialog dialog;
    private View view;
    private ImageView iv_month_last, iv_month_next, iv_year_next, iv_year_last, ivClose;
    private TextView tv_year, tv_month;
    private MonthDateView dateView;
    private Button btn_confirm;
    private String dates;

    public DateAlertDialog(Context context) {

        dialog = new AlertDialog.Builder(context).create();
        view = LayoutInflater.from(context).inflate(R.layout.air_calendar_dl_date, null);
        dialog.setCanceledOnTouchOutside(true);

        dateView = (MonthDateView) view.findViewById(R.id.monthDateView);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
//        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        iv_month_last = (ImageView) view.findViewById(R.id.iv_month_last);
        iv_month_next = (ImageView) view.findViewById(R.id.iv_month_next);
        iv_year_next = (ImageView) view.findViewById(R.id.iv_year_next);
        iv_year_last = (ImageView) view.findViewById(R.id.iv_year_last);
        tv_year = (TextView) view.findViewById(R.id.tv_year);
        tv_month = (TextView) view.findViewById(R.id.tv_month);

        dateView.setmDaySize(13);
        dateView.setmNormalTextColor(context.getResources().getColor(R.color.black_32));
        dateView.setmSelectBGColor(context.getResources().getColor(R.color.blue_28));
        dateView.setmSelectedTextColor(context.getResources().getColor(R.color.white));
        dateView.setTextView(tv_year, tv_month);

//        ivClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.hide();
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
        String months = month + "";
        String days = day + "";
        if (month < 10) {
            months = "0" + month + "";
        }
        if (day < 10) {
            days = "0" + day + "";
        }

        dates = year + "-" + months + "-" + days;
        return dates;
    }

    public void show() {
        dialog.show();
        dialog.setContentView(view);
    }

    public void hide() {
        dialog.hide();
    }

    public void dismiss() {
        dialog.dismiss();
    }

}
