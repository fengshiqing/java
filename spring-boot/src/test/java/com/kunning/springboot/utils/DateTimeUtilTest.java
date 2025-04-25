/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
        System.out.println("【是否是闰年：】" + DateTimeUtil.isLeapYear(1234));
    }

    @Test
    void aaa() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 3, 30, 18, 30, 30);
        System.out.println("【当月第一天是今年的第几天（闰年）：】" + localDateTime.getMonth().firstDayOfYear(true)); // 当月第一天是今年的第几天
        System.out.println("【当月第一天是今年的第几天（平年）：】" + localDateTime.getMonth().firstDayOfYear(false));
        System.out.println("【当天是今年的第几天：】" + localDateTime.getDayOfYear()); // 当月第一天是今年的第几天
        System.out.println("【当天是周几（英文）：】" + localDateTime.getDayOfWeek());
        System.out.println("【当天是周几（数字）：】" + localDateTime.getDayOfWeek().getValue());

        System.out.println();

        System.out.println("【当前月的第一个周日】" + LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY))); // 当前月的第一个周日，下面的dayOfWeekInMonth更灵活,可以定义第几周
        System.out.println("【当前月的第一个周日：】" + LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY)));
        System.out.println("【上个月的最后一个周日：】" + LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.SUNDAY)));
        System.out.println("【当前月的最后一个周日：】" + LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(-1, DayOfWeek.SUNDAY)));

        System.out.println();

        System.out.println("【明年的第一天：】" + LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.println("【下周五：】" + LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
        System.out.println("【下周二：】" + LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
        System.out.println("【下个周日】" + LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))); // 下个周日，如果当天就是周日，返回当天
        System.out.println("【当月最后一天】" + LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
    }
}
