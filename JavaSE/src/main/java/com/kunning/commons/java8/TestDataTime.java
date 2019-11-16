package com.kunning.commons.java8;


import org.junit.Test;

import java.time.*;

/**
 * Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
 * 在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
 * 非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
 * 设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
 * 时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
 * <p>
 * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
 * <p>
 * Local(本地) − 简化了日期时间的处理，没有时区的问题。
 * Zoned(时区) − 通过制定的时区处理日期时间。
 * <p>
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
 */
public class TestDataTime {

    @Test
    public void testLocalDateTime() {

        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("【当前时间戳: 】" + currentTime);
        System.out.println("【获取时间戳的年月日: 】" + currentTime.toLocalDate());
        System.out.println("【获取时间戳的时分秒: 】" + currentTime.toLocalTime());
        printDataTime(currentTime);

        LocalDateTime date2 = currentTime.withYear(2012).withMonth(12).withDayOfMonth(12).withHour(12).withMinute(12).withSecond(12);
        printDataTime(date2);

        System.out.println("【构造一个年月日】: " + LocalDate.of(2014, Month.DECEMBER, 12));
        System.out.println("【构造一个年月日】: " + LocalDate.of(2014, 12, 12));
        System.out.println("【构造一个时分】: " + LocalTime.of(22, 15));
        System.out.println("【构造一个时分秒】: " + LocalTime.of(22, 15, 18));

        System.out.println("【解析时间字符串】: " + LocalTime.parse("20:15:30"));
        System.out.println("【解析时间字符串】: " + LocalTime.parse("20:15:30"));
        System.out.println("【解析时间字符串】: " + LocalTime.parse("20:15:30"));
}

    public void printDataTime(LocalDateTime localDateTime) {
        System.out.println("【年】" + localDateTime.getYear() + "【月】" + localDateTime.getMonthValue());
        System.out.println("【日】" + localDateTime.getDayOfMonth());
        System.out.println("【时】" + localDateTime.getHour());
        System.out.println("【分】" + localDateTime.getMinute());
        System.out.println("【秒】" + localDateTime.getSecond());
    }

}
