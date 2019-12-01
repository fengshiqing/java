package com.kunning.SpringBoot.utils;

import com.kunning.springboot.utils.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;

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

    // =====================================================java8=====================================================

    @Test
    void getCurrentDate() {
        System.out.println("【获取当前日期：】" + DateTimeUtil.getCurrentDate());
    }


    @Test
    void getCurrentTime() {
        System.out.println("【获取当前时间：】" + DateTimeUtil.getCurrentTime());
    }

    @Test
    void getCurrentDateTime() {
        System.out.println("【获取当前日期时间：】" + DateTimeUtil.getCurrentDateTime());
    }

    @Test
    void timeInterval() throws Exception {
        System.out.println("【时间差/天】" + DateTimeUtil.dateInterval("2019-11-11", "2019-11-02"));
    }

    @Test
    void isLeapYear() {
        DateTimeUtil.isLeapYear(1234);
    }

}
