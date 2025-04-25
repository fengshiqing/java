package com.kunning.springboot.utils;

import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 功能描述：日期时间工具类。
 *
 * @author 冯仕清
 */
public class DateTimeUtil {

    /**
     * 私有化构造函数
     */
    private DateTimeUtil() {
    }

    /**
     * 功能描述：定义日期时间的格式。
     */
    // 格式可以参考这个：https://www.cnblogs.com/jyiqing/p/6858224.html
    interface FORMAT {
        String format_1 = "yyyy-MM-dd HH:mm:ss";
        String format_2 = "yyyyMMddHHmmss";
        String format_3 = "yyyy-MM-dd";
        String format_4 = "HH:mm:ss";
    }

    private static final SimpleDateFormat SDF_1 = new SimpleDateFormat(FORMAT.format_1);
    private static final SimpleDateFormat SDF_2 = new SimpleDateFormat(FORMAT.format_2);
    private static final SimpleDateFormat SDF_3 = new SimpleDateFormat(FORMAT.format_3);
    private static final SimpleDateFormat SDF_4 = new SimpleDateFormat(FORMAT.format_4);

    // ====================================================java8之前====================================================

    /**
     * 根据一个日期，返回是星期几的字符串
     */
    public static String getWeek(String strDate) {
        // 再转换为时间
        Date date = null;
        try {
            date = SDF_3.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return new SimpleDateFormat("EEEE").format(calendar.getTime());
    }

    /**
     * 取当天(短日期类型)
     */
    public static String getShortToday() {
        Date today = new Date();
        return SDF_4.format(today);
    }

    /**
     * 取当天(长日期类型)
     */
    public static String getLongToday() {
        Date today = new Date();
        return SDF_1.format(today);
    }

    /**
     * 取当天零点零分零秒
     */
    public static String getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        //如果没有这种设定的话回去系统的当期的时间
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = new Date(calendar.getTimeInMillis());
        return SDF_1.format(date);
    }

    /**
     * 取当天23点59分59秒
     */
    public static String getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date = new Date(calendar.getTimeInMillis());
        return SDF_1.format(date);
    }

    /**
     * 取特定日期的零点零分零秒
     */
    public static String getDateStart(String strDate) {
        if (null == strDate || "".equals(strDate.trim())) {
            return "";
        }
        Date date = null;
        try {
            date = SDF_4.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return SDF_1.format(date);
    }

    /**
     * 取特定日期的零点零分零秒
     */
    public static String getDateEnd(String strDate) {
        if (null == strDate || "".equals(strDate.trim())) {
            return "";
        }
        Date date = null;
        try {
            date = SDF_4.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //这样能够取到59分59秒
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return SDF_1.format(calendar.getTime());
    }

    /**
     * 取当周周一
     */
    public static String getMondayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);
        /*calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);*/
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取当周周日
     */
    public static String getSundayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);
        /*calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);*/
        //以上取得的是周一
        calendar.add(Calendar.DATE, 6);
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取上周周一
     */
    public static String getMondayOfPreviousWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);
        calendar.add(Calendar.DATE, -7);
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取上周周日
     */
    public static String getSundayOfPreviousWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);
        calendar.add(Calendar.DATE, -1);
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取下周周一
     */
    public static String getMondayOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);  // 当周周一
        calendar.add(Calendar.DATE, 7);
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取下周周日
     */
    public static String getSundayOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 1);  // 当周周一
        calendar.add(Calendar.DATE, 13);
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取当月1号
     */
    public static String getFirstDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);    // 设为当前月的1号
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取当月最后一天
     */
    public static String getLastDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);    // 设为当前月的1号
        calendar.add(Calendar.MONTH, 1);   // 加一个月，变为下月的1号
        calendar.add(Calendar.DATE, -1);   // 减去一天，变为当月最后一天
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取上月1号
     */
    public static String getFirstDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);    // 设为当前月的1号
        calendar.add(Calendar.MONTH, -1);  // 减一个月，变为上月的1号
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取上月最后一天
     */
    public static String getLastDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);    // 设为当前月的1号
        calendar.add(Calendar.DATE, -1);   // 减去一天，变为上月最后一天
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取下月1号
     */
    public static String getFirstDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);    // 加一个月
        calendar.set(Calendar.DATE, 1);     // 把日期设置为当月第一天
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 取下月最后一天
     */
    public static String getLastDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);// 加一个月
        calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        return SDF_4.format(calendar.getTime());
    }

    /**
     * 获得本年有多少天
     */
    public static int getTotalDaysOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        calendar.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取某年某月的最后一天
     */
    public static int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }


    /**
     * 获得本年第一天的日期
     */
    public static String getFirstDayOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date date = new Date(calendar.getTimeInMillis());
        return SDF_4.format(date);
    }

    /**
     * 获得本年最后一天的日期
     */
    public static String getLastDayOfThisYear() {
        Date date = new Date();
        String years = new SimpleDateFormat("yyyy").format(date);
        return years + "-12-31";
    }

    /**
     * 获得上年第一天的日期 *
     */
    public static String getFirstDayOfPreviousYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);    // 若是 明年改为正整数1即可
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date date = new Date(calendar.getTimeInMillis());
        return SDF_4.format(date);
    }

    /**
     *
     */
    public static void main(String[] args) {
        System.out.println("/*--------------------------获得上年第一天的日期--------------------------*/");
        System.out.println("获得上年第一天的日期  :   " + getFirstDayOfPreviousYear());
        System.out.println("/*--------------------------获得本年最后一天的日期--------------------------*/");
        System.out.println("获得本年最后一天的日期 :   " + getLastDayOfThisYear());
        System.out.println("/*--------------------------获得本年第一天的日期--------------------------*/");
        System.out.println("获得本年第一天的日期  :   " + getFirstDayOfThisYear());
        System.out.println("/*--------------------------获取某年某月的最后一天--------------------------*/");
        System.out.println("获取某年某月的最后一天 :   " + getLastDayOfMonth(2012, 5));

        System.out.println("/*--------------------------获得本年有多少天--------------------------*/");
        System.out.println("获得本年有多少天        :   " + getTotalDaysOfThisYear());
        System.out.println("/*--------------------------下月1号和最后一天--------------------------*/");
        System.out.println("下月1号                :   " + getFirstDayOfNextMonth());
        System.out.println("下月最后一天          :   " + getLastDayOfNextMonth());
        System.out.println("/*--------------------------上月1号和最后一天--------------------------*/");
        System.out.println("上月1号                :   " + getFirstDayOfPreviousMonth());
        System.out.println("上月最后一天          :   " + getLastDayOfPreviousMonth());
        System.out.println("/*--------------------------当月1号和最后一天--------------------------*/");
        System.out.println("当月1号                :   " + getFirstDayOfThisMonth());
        System.out.println("当月最后一天          :   " + getLastDayOfThisMonth());
        System.out.println("/*--------------------------当月1号和最后一天--------------------------*/");
        System.out.println("当月1号                :   " + getFirstDayOfThisMonth());
        System.out.println("当月最后一天          :   " + getLastDayOfThisMonth());
        System.out.println("/*--------------------------下周周一和周日--------------------------*/");
        System.out.println("下周周一                :   " + getMondayOfNextWeek());
        System.out.println("下周周日                :   " + getSundayOfNextWeek());
        System.out.println("/*--------------------------上周周一和周日--------------------------*/");
        System.out.println("上周周一                :   " + getMondayOfPreviousWeek());
        System.out.println("上周周日                :   " + getSundayOfPreviousWeek());
        System.out.println("/*--------------------------当周周一和周日--------------------------*/");
        System.out.println("当周周一                :   " + getMondayOfThisWeek());
        System.out.println("当周周日                :   " + getSundayOfThisWeek());
        System.out.println("/*--------------------------特定日期(零点和23点)--------------------------*/");
        System.out.println("特定日期(23点59分59秒) :   " + getDateEnd("2012-05-12"));
        System.out.println("特定日期(零点零分零秒)    :   " + getDateStart("2012-05-12"));
        System.out.println("/*--------------------------当天(零点和23点)--------------------------*/");
        System.out.println("当天(23点59分59秒)   :   " + getTodayEnd());
        System.out.println("当天(零点零分零秒)  :   " + getTodayStart());
        System.out.println("/*---------------------------当天(长日期格式和短日期格式)-------------------------*/");
        System.out.println("当天(长日期格式)       :   " + getLongToday());
        System.out.println("当天(短日期格式)       :   " + getShortToday());
        System.out.println("/*---------------------------当天(长日期格式和短日期格式)-------------------------*/");
        System.out.println("根据一个日期，返回是星期几的字符串       :   " + getWeek("2012-05-12"));
    }


    // =====================================================java8=====================================================
    // 这个讲解的很不错：https://www.toutiao.com/i6736371516507685390/

    /**
     * 功能描述：获取默认格式"yyyy-MM-dd"的当前日期。
     *
     * @return 指定格式的当前日期
     */
    public static String getCurrentDate() {
        return DateTimeUtil.getCurrentDate(FORMAT.format_3);
    }

    /**
     * 功能描述：获取特定格式的当前日期
     *
     * @param pattern 日期的格式
     *
     * @return 指定格式的当前日期
     */
    public static String getCurrentDate(String pattern) {
        Objects.requireNonNull(pattern, "参数【pattern】不能为null");
        LocalDate localDate = LocalDate.now(); // 默认的日期时间格式为："2019-11-24"
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    /**
     * 功能描述：获取默认格式"HH:mm:ss"的当前时间
     *
     * @return 默认格式的当前时间
     */
    public static String getCurrentTime() {
        return DateTimeUtil.getCurrentTime(FORMAT.format_4);
    }

    /**
     * 功能描述：获取特定格式的当前时间
     *
     * @param pattern 时间的格式
     *
     * @return 指定格式的当前时间
     */
    public static String getCurrentTime(String pattern) {
        Objects.requireNonNull(pattern, "参数【pattern】不能为null");
        LocalTime localTime = LocalTime.now(); // 默认的日期时间格式为：18:19:13.778
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localTime.format(dateTimeFormatter);
    }


    /**
     * 功能描述：获取默认格式"yyyy-MM-dd HH:mm:ss"的的当前日期时间
     *
     * @return 默认格式的当前日期时间
     */
    public static String getCurrentDateTime() {
        return getCurrentDateTime(FORMAT.format_1);
    }

    /**
     * 功能描述：获取特定格式的当前日期时间
     *
     * @param pattern 日期时间的格式
     *
     * @return 指定格式的当前日期时间
     */
    public static String getCurrentDateTime(@NonNull String pattern) {
        Objects.requireNonNull(pattern, "参数【pattern】不能为null");
        LocalDateTime localDateTime = LocalDateTime.now(); // 默认的日期时间格式为：2019-11-24T10:40:13.778
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 功能描述：将 long型时间戳 转换为 固定格式的 日期字符串。
     * @param timeStamp 时间戳
     * @return 日期字符串
     */
    public static String formatTime(long timeStamp) {
        return formatTime(timeStamp, FORMAT.format_1);
    }

    /**
     * 功能描述：将 long型时间戳 转换为 固定格式的 日期字符串。
     * @param timeStamp 时间戳
     * @return 日期字符串
     */
    public static String formatTime(long timeStamp, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timeStamp/1000, 0, ZoneOffset.ofHours(8));
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 功能描述：获取两个时间点的差值。
     *
     * @param start 开始时间，格式："2019-11-24"
     * @param end   结束时间，格式："2019-11-25"
     *
     * @return 时间差
     */
    // https://blog.csdn.net/neweastsun/article/details/88770592
    public static long dateInterval(String start, String end) throws Exception {
        LocalDate from = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE); // 格式化日期
        LocalDate to = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE); // 格式化日期
        Period period = Period.between(from, to);
        if (period.isNegative()) {
            throw new Exception("【开始时间不能大于结束时间】");
        }
        // Duration duration = Duration.between(from, to);
        System.out.println("【差值/年：】" + period.getYears());
        System.out.println("【差值/月：】" + period.getMonths());
        System.out.println("【差值/日：】" + period.getDays());
        return period.getDays();
    }

    /**
     * 功能描述：判断是否闰年
     *
     * @param year 年份
     *
     * @return true闰年，false平年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}
