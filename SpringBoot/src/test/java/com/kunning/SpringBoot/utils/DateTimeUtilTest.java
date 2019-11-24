package com.kunning.SpringBoot.utils;

import com.kunning.springboot.utils.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    @Test
    void getWeek() {
//        DateTimeUtil.getWeek("20191124"); // 格式不对，会报错
        System.out.println(DateTimeUtil.getWeek("2019-11-24"));
        System.out.println(TimeZone.getDefault().toZoneId());
    }

    @Test
    void getShortToday() {
    }

    @Test
    void getLongToday() {
    }

    @Test
    void getTodayStart() {
    }

    @Test
    void getTodayEnd() {
    }

    @Test
    void getDateStart() {
    }

    @Test
    void getDateEnd() {
    }

    @Test
    void getMondayOfThisWeek() {
    }

    @Test
    void getSundayOfThisWeek() {
    }

    @Test
    void getMondayOfPreviousWeek() {
    }

    @Test
    void getSundayOfPreviousWeek() {
    }

    @Test
    void getMondayOfNextWeek() {
    }

    @Test
    void getSundayOfNextWeek() {
    }

    @Test
    void getFirstDayOfThisMonth() {
    }

    @Test
    void getLastDayOfThisMonth() {
    }

    @Test
    void getFirstDayOfPreviousMonth() {
    }

    @Test
    void getLastDayOfPreviousMonth() {
    }

    @Test
    void getFirstDayOfNextMonth() {
    }

    @Test
    void getLastDayOfNextMonth() {
    }

    @Test
    void getTotalDaysOfThisYear() {
    }

    @Test
    void getLastDayOfMonth() {
    }

    @Test
    void isLeapYear() {
        DateTimeUtil.isLeapYear(1234);
    }

    @Test
    void getFirstDayOfThisYear() {
    }

    @Test
    void getLastDayOfThisYear() {
    }

    @Test
    void getFirstDayOfPreviousYear() {

    }

    @Test
    void getDays() {
    }

    @Test
    void main() {
    }


    @Test
    void getCurrentDateTime() {
        DateTimeUtil.getCurrentDateTime(null);
    }

}
