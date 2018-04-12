package com.longe.myresource.view.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * MonthDateView  自定义日历
 *
 * @author 左桂清
 * @version 1.0, 2017/2/24 0024
 */
public class MonthDateView extends View {
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private Paint mPaint;
    //各种颜色
    private int mNormalTextColor = Color.parseColor("#000000");
    private int mSelectedTextColor = Color.parseColor("#ffffff");
    private int mSelectBGColor = Color.parseColor("#1FC2F3");
    private int mCurrTextColor = Color.parseColor("#ff0000");
    //当前时间，创建的时候初始化
    private int mCurrYear, mCurrMonth, mCurrDay;
    //选择的时间，点击确定
    private int mSelYear, mSelMonth, mSelDay;
    private int mColumnSize, mRowSize;
    private DisplayMetrics mDisplayMetrics;
    private int mDaySize = 18;
    private TextView tvYear, tvMonth;
    private int[][] daysArray;
    private String dayString;
    private DateClick dateClick;

    public MonthDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDisplayMetrics = getResources().getDisplayMetrics();
        Calendar calendar = Calendar.getInstance();
        mPaint = new Paint();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        setSelectYearMonth(mCurrYear, mCurrMonth, mCurrDay);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();

        daysArray = new int[6][7];

        mPaint.setAntiAlias(true);
        //抗锯齿
        mPaint.setTextSize(mDaySize * mDisplayMetrics.scaledDensity);

        int monthDays = getMonthDays(mSelYear, mSelMonth);
        int weekNumber = getFirstDayWeek(mSelYear, mSelMonth);
//		Log.d("DateView", "DateView:" + mSelMonth+"月1号周" + weekNumber);

        for (int day = 0; day < monthDays; day++) {
            dayString = (day + 1) + "";

            int column = (day + weekNumber - 1) % 7;
            int row = (day + weekNumber - 1) / 7;

            daysArray[row][column] = day + 1;

            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);

            //绘制背景圆形
            if (dayString.equals(mSelDay + "")) {
                mPaint.setColor(mSelectBGColor);
                float circleX = (float) (mColumnSize * column + mColumnSize * 0.5);
                float circleY = (float) (mRowSize * row + mRowSize * 0.5);
                canvas.drawCircle(circleX, circleY, mRowSize / 3, mPaint);
            }

            //绘制文字
            if (dayString.equals(mSelDay + "")) {
                mPaint.setColor(mSelectedTextColor);
            } else if (dayString.equals(mCurrDay + "") && mCurrDay != mSelDay
                    && mCurrMonth == mSelMonth && mCurrYear == mSelYear) {
                //当月选中其他日期，当前日 文字颜色
                mPaint.setColor(mCurrTextColor);
            } else {
                mPaint.setColor(mNormalTextColor);
            }
            canvas.drawText(dayString, startX, startY, mPaint);

            if (tvYear != null) {
                //传入控件赋值   年
                tvYear.setText(mSelYear + "");
            }
            //月
            if (tvMonth != null) {
                tvMonth.setText((mSelMonth + 1) + "月");
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private int downX = 0, downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch (eventCode) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int upY = (int) event.getY();
                if (Math.abs(upX - downX) < 10 && Math.abs(upY - downY) < 10) {//点击事件
                    performClick();
                    doClickAction((upX + downX) / 2, (upY + downY) / 2);
                }
                break;
        }
        return true;
    }

    /**
     * 初始化列宽行高
     */
    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
    }

    /**
     * 设置年月
     *
     * @param year
     * @param month
     */
    private void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }

    /**
     * 执行点击事件
     *
     * @param x
     * @param y
     */
    private void doClickAction(int x, int y) {
        int row = y / mRowSize;
        int column = x / mColumnSize;
        setSelectYearMonth(mSelYear, mSelMonth, daysArray[row][column]);
        invalidate();

        //执行activity发送过来的点击处理事件
        if (dateClick != null) {
            dateClick.onClickOnDate();
        }
    }


    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param year  年份
     * @param month 月份，传入系统获取的，不需要正常的
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
//		Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 左点击，日历向后翻页(月)
     */
    public void onLeftClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 0) {//若果是1月份，则变成12月份
            year = mSelYear - 1;
            month = 11;
        } else if (getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month - 1;
            day = getMonthDays(year, month);
        } else {
            month = month - 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 右点击，日历向前翻页（月）
     */
    public void onRightClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 11) {//若果是12月份，则变成1月份
            year = mSelYear + 1;
            month = 0;
        } else if (getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month + 1;
            day = getMonthDays(year, month);
        } else {
            month = month + 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 减一年（年）
     */
    public void onCutYearClick() {
        int year = mSelYear - 1;
        setSelectYearMonth(year, mCurrMonth, mCurrDay);
        invalidate();
    }

    /**
     * 加一年（年）
     */
    public void onAddYearClick() {
        int year = mSelYear + 1;
        setSelectYearMonth(year, mCurrMonth, mCurrDay);
        invalidate();
    }

    /**
     * 获取选择的年份
     *
     * @return
     */
    public int getmSelYear() {
        return mSelYear;
    }

    /**
     * 获取选择的月份
     *
     * @return
     */
    public int getmSelMonth() {
        return mSelMonth;
    }

    /**
     * 获取选择的日期
     *
     * @param
     */
    public int getmSelDay() {
        return this.mSelDay;
    }

    /**
     * 普通日期的字体颜色，默认黑色
     *
     * @param mNormalTextColor
     */
    public void setmNormalTextColor(int mNormalTextColor) {
        this.mNormalTextColor = mNormalTextColor;
    }

    /**
     * 选择日期的颜色，默认为白色
     *
     * @param mSelectedTextColor
     */
    public void setmSelectedTextColor(int mSelectedTextColor) {
        this.mSelectedTextColor = mSelectedTextColor;
    }

    /**
     * 选中日期的背景颜色，默认蓝色
     *
     * @param mSelectBGColor
     */
    public void setmSelectBGColor(int mSelectBGColor) {
        this.mSelectBGColor = mSelectBGColor;
    }

    /**
     * 当前日期，默认黑色
     *
     * @param mCurrTextColor
     */
    public void setmCurrTextColor(int mCurrTextColor) {
        this.mCurrTextColor = mCurrTextColor;
    }

    /**
     * 日期的大小，默认18sp
     *
     * @param mDaySize
     */
    public void setmDaySize(int mDaySize) {
        this.mDaySize = mDaySize;
    }

    /**
     * 设置显示当前日期的控件
     *
     * @param tv_year  显示年
     * @param tv_month 显示月
     */
    public void setTextView(TextView tv_year, TextView tv_month) {
        this.tvYear = tv_year;
        this.tvMonth = tv_month;
        invalidate();
    }

    /**
     * 设置日期的点击回调事件
     *
     * @author shiwei.deng
     */
    public interface DateClick {
        public void onClickOnDate();
    }

    /**
     * 设置日期点击事件
     *
     * @param dateClick
     */
    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }
}
