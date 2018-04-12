package com.longe.myresource.view.air_calendar;

import android.content.Context;
import android.content.res.TypedArray;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.longe.myresource.R;
import com.longe.myresource.util.Ut;
import com.longe.myresource.view.air_calendar.bean.DateRange;
import com.longe.myresource.view.air_calendar.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by blanke on 16-12-15.
 */

public class AirCalendarView extends FrameLayout {

    private TextView tvWeekStart;
    private TextView tvWeekEnd;

    public interface OnSelectedDayListener {
        void onDaySelected(Calendar startDay, Calendar endDay);
    }

    private RecyclerView mRecyclerView;
    private Calendar toDay;
    private MonthAdapter mMonthAdapter;
    private DateRange dataRange;
    private int toDayYear;
    private int toDayMonth;
    private int toDayPosition;//今天这个月的position
    private TextView startDateTv, endDateTv;

    public AirCalendarView(Context context) {
        super(context);
        init(null);
    }

    public AirCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AirCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AirCalendarView);
        int visibleYearCount = typedArray.getInt(R.styleable.AirCalendarView_visibleYearCount, 1);
        int daySelectOffset = typedArray.getInt(R.styleable.AirCalendarView_daySelectOffset, 1);
        boolean isSelectFuture = typedArray.getBoolean(R.styleable.AirCalendarView_isSelectFuture, true);
        typedArray.recycle();
        dataRange = new DateRange(isSelectFuture, daySelectOffset);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.air_view, this, false);
        addView(rootView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.view_aircalendar_rv_date);
        startDateTv = (TextView) rootView.findViewById(R.id.view_aircalendar_tv_start_date);
        endDateTv = (TextView) rootView.findViewById(R.id.view_aircalendar_tv_end_date);
        tvWeekStart = (TextView) rootView.findViewById(R.id.tv_week_start);
        tvWeekEnd = (TextView) rootView.findViewById(R.id.tv_week_end);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        toDay = CalendarUtils.getToDay();
        toDayYear = toDay.get(Calendar.YEAR);
        toDayMonth = toDay.get(Calendar.MONTH);
        List<Calendar> years = new ArrayList<>();
        if (isSelectFuture()) {
            for (int i = 1; i < visibleYearCount; i++) {
                years.addAll(getYearMonths(i - 1 + toDayYear));
            }
            toDayPosition = toDayMonth;
        } else {
            toDayPosition = toDayMonth + 12 * (visibleYearCount - 1);
            for (int i = visibleYearCount; i > 0; i--) {
                years.addAll(getYearMonths(-i + 1 + toDayYear));
            }
        }
        mMonthAdapter = new MonthAdapter(years, dataRange, mRecyclerView);
        mRecyclerView.setAdapter(mMonthAdapter);
        mRecyclerView.scrollToPosition(toDayPosition);
    }

    private List<Calendar> getYearMonths(int year) {
        List<Calendar> months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Calendar calendar = CalendarUtils.getCalendar(year, i, 1);
            if (isSelectFuture()){

            }else {
                if (year == toDayYear && i > toDayMonth) {
                    break;
                }
            }
            months.add(calendar);
        }
        return months;
    }


    public Calendar getStartDate() {
        return mMonthAdapter.getDateRange().getStartDate();
    }

    public Calendar getEndDate() {
        return mMonthAdapter.getDateRange().getEndDate();
    }

    public void clearSelect() {
        mMonthAdapter.clearSelect();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public boolean isSelectFuture() {
        return dataRange.isSelectFuture();
    }

    public int getDaySelectOffset() {
        return dataRange.getDaySelectOffset();
    }

    public OnSelectedDayListener getOnSelectedDayListener() {
        return mMonthAdapter.getOnSelectedDayListener();
    }

    private String getDateStr(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
//        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        return (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH)+"日";
    }

    private String getDWStr(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
//        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        return "星期"+getWeekText(calendar.get(Calendar.DAY_OF_WEEK)-1);
    }

    public void setOnSelectedDayListener(final OnSelectedDayListener onSelectedDayListener) {
        this.mMonthAdapter.setOnSelectedDayListener(new OnSelectedDayListener() {
            @Override
            public void onDaySelected(Calendar startDay, Calendar endDay) {
                tvWeekStart.setText(getDWStr(startDay));
                tvWeekEnd.setText(getDWStr(endDay));
                startDateTv.setText(getDateStr(startDay));
                endDateTv.setText(getDateStr(endDay));
                onSelectedDayListener.onDaySelected(startDay, endDay);
            }
        });
    }

    private String getWeekText(int week){
        Ut.e("week",week);
        String w = "";
        switch (week){
            case 1:
                w = "一";
                break;
            case 2:
                w = "二";
                break;
            case 3:
                w = "三";
                break;
            case 4:
                w = "四";
                break;
            case 5:
                w = "五";
                break;
            case 6:
                w = "六";
                break;
            case 0:
                w = "日";
                break;
        }
        return w;
    }
}
