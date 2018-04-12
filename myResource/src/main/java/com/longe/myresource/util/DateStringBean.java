package com.longe.myresource.util;

/**
 * DateStringBean   获取时间
 *
 * @author ZGQ
 * @version 1.0, 2018/1/18 0018
 */
public class DateStringBean {

    private String startDate;
    private String endDate;
    private String startDateNum;
    private String endDateNum;

    public DateStringBean() {
    }

    public DateStringBean(String startDate, String endDate, String startDateNum, String endDateNum) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateNum = startDateNum;
        this.endDateNum = endDateNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDateNum() {
        return startDateNum;
    }

    public void setStartDateNum(String startDateNum) {
        this.startDateNum = startDateNum;
    }

    public String getEndDateNum() {
        return endDateNum;
    }

    public void setEndDateNum(String endDateNum) {
        this.endDateNum = endDateNum;
    }
}
