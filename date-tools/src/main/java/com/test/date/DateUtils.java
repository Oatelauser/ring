package com.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static com.test.date.Dates.from;

/**
 * 日期工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class DateUtils {

    /**
     * 字符串日期格式化
     *
     * @param str           字符串日期
     * @param sourcePattern 原始日期格式
     * @param targetPattern 目标格式化格式
     * @return 格式化后的日期
     */
    public static String format(String str, String sourcePattern, String targetPattern) {
        Asserts.illegalArgument(str);
        Asserts.illegalArgument(sourcePattern);
        Asserts.illegalArgument(targetPattern);
        DateFormat sdf = new SimpleDateFormat(targetPattern);
        return sdf.format(from(str, sourcePattern));
    }

    /**
     * 字符串日期格式化
     *
     * @param date    日期对象
     * @param pattern 目标格式化格式
     * @return 格式化后的日期
     */
    public static String format(Date date, String pattern) {
        Asserts.illegalArgument(date);
        Asserts.illegalArgument(pattern);
        DateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 日期对象转字符串
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 字符串日期
     */
    public static String format(LocalTime date, String pattern) {
        Asserts.illegalArgument(date);
        Asserts.illegalArgument(pattern);
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期对象转字符串
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 字符串日期
     */
    public static String format(LocalDate date, String pattern) {
        return format(date, pattern, ZoneId.systemDefault());
    }

    /**
     * 日期对象转字符串
     * --------------------------------------------------------------------------------------------
     * 示例说明：
     * <p>
     * 格林威治时区：ZoneId.of("UTC") 和 ZoneId.of("GMT")
     * <p>
     * 北京时间，东八区： ZoneId.of("Asia/Shanghai") 和 ZoneId.of("GMT+8")
     * Asia/Shanghai和GMT+8都是用于表示北京时间的方式，它们都代表同一个时区。但是在Java中，推荐使用"Asia/Shanghai"这个标识符来表示北京时间。
     * 因为它更准确地反映了这个时区的历史和规则变化，可以更好地处理夏令时等问题。而GMT+8只是一个表示时差的简单方式，没有考虑到夏令时等复杂情况。
     * <p>
     * 美东时区：ZoneId.of("America/New_York") 和 ZoneId.of("US/Eastern")
     * America/New_York和US/Eastern都是表示美东时间的时区标识符，但是在Java中，推荐使用"America/New_York"这个标识符来表示美东时间。
     * 因为它更准确地反映了这个时区的历史和规则变化，可以更好地处理夏令时等问题。而US/Eastern是一个较旧的标识符，更容易导致时区错误
     * --------------------------------------------------------------------------------------------
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @param zoneId  时区
     * @return 字符串日期
     */
    public static String format(LocalDate date, String pattern, ZoneId zoneId) {
        Asserts.illegalArgument(date);
        Asserts.illegalArgument(pattern);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return date.atStartOfDay().atZone(ZoneId.systemDefault())
                .withZoneSameInstant(zoneId).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期对象转字符串
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 字符串日期
     */
    public static String format(LocalDateTime date, String pattern) {
        return format(date, pattern, ZoneId.systemDefault());
    }

    /**
     * 日期对象转字符串
     * --------------------------------------------------------------------------------------------
     * 示例说明：
     * <p>
     * 格林威治时区：ZoneId.of("UTC") 和 ZoneId.of("GMT")
     * <p>
     * 北京时间，东八区： ZoneId.of("Asia/Shanghai") 和 ZoneId.of("GMT+8")
     * Asia/Shanghai和GMT+8都是用于表示北京时间的方式，它们都代表同一个时区。但是在Java中，推荐使用"Asia/Shanghai"这个标识符来表示北京时间。
     * 因为它更准确地反映了这个时区的历史和规则变化，可以更好地处理夏令时等问题。而GMT+8只是一个表示时差的简单方式，没有考虑到夏令时等复杂情况。
     * <p>
     * 美东时区：ZoneId.of("America/New_York") 和 ZoneId.of("US/Eastern")
     * America/New_York和US/Eastern都是表示美东时间的时区标识符，但是在Java中，推荐使用"America/New_York"这个标识符来表示美东时间。
     * 因为它更准确地反映了这个时区的历史和规则变化，可以更好地处理夏令时等问题。而US/Eastern是一个较旧的标识符，更容易导致时区错误
     * --------------------------------------------------------------------------------------------
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @param zoneId  时区
     * @return 字符串日期
     */
    public static String format(LocalDateTime date, String pattern, ZoneId zoneId) {
        Asserts.illegalArgument(date);
        Asserts.illegalArgument(pattern);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return date.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将日期对象转换为整数日期
     *
     * @param date    日期对象
     * @param pattern 日期 格式
     * @return 整数日期
     */
    public static long dateToInt(Date date, String pattern) {
        Asserts.illegalArgument(date);
        Asserts.illegalArgument(pattern);
        return Long.parseLong(format(date, pattern));
    }

    /**
     * 将时间对象转换为整数类型
     *
     * @param date    时间对象
     * @param pattern 日期格式
     * @return 整数类型日期
     */
    public static long dateToInt(LocalTime date, String pattern) {
        Asserts.illegalArgument(date);
        pattern = Asserts.requireElseGet(pattern, DatePatterns.HHMMSS);
        return Long.parseLong(date.format(DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 将日期对象转换为整数类型
     *
     * @param date    日期对象
     * @param pattern 日期格式对象
     * @return 整数类型日期
     */
    public static long dateToInt(LocalDate date, String pattern) {
        Asserts.illegalArgument(date);
        pattern = Asserts.requireElseGet(pattern, DatePatterns.YYYYMMDD);
        return dateToInt(date.atStartOfDay(), pattern);
    }

    /**
     * 日期类型转换为整数类型
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 整数类型日期
     */
    public static long dateToInt(LocalDateTime date, String pattern) {
        Asserts.illegalArgument(date);
        pattern = Asserts.requireElseGet(pattern, DatePatterns.YYYYMMDDHHMMSS);
        return Long.parseLong(date.format(DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 比较日期大小，firstDate大于等于secondDate 返回true,否则返回false
     *
     * @param date1   日期字符串
     * @param date2   日期字符串
     * @param pattern 日期格式
     * @return 1:date1&gt;date2、0:date1=date2 -1:date1&lt;date2
     */
    public static int compareTo(String date1, String date2, String pattern) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        Asserts.illegalArgument(pattern);
        try {
            DateFormat sdf = new SimpleDateFormat(pattern);
            Date first = sdf.parse(date1);
            Date second = sdf.parse(date2);
            return first.compareTo(second);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数");
        }
    }

    /**
     * 比较日期大小，firstDate大于等于secondDate 返回true,否则返回false
     *
     * @param date1 日期字符串
     * @param date2 日期字符串
     * @return 1:date1&gt;date2、0:date1=date2 -1:date1&lt;date2
     */
    public static int compareTo(Date date1, Date date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.compareTo(date2);
    }

    /**
     * 日期大小比较
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return 1:date1&gt;date2、0:date1=date2 -1:date1&lt;date2
     */
    public static int compareTo(LocalDateTime date1, LocalDateTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.compareTo(date2);
    }

    /**
     * 比较两个日期的大小
     * 如：2023-05-14 12:56:28比2023-05-14 12:56:29小
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return true-date1小于date2,false-date1小于date2
     */
    public static boolean isBefore(LocalDateTime date1, LocalDateTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.isBefore(date2);
    }

    /**
     * 比较两个日期的大小
     * 如：2023-05-14 12:56:29比2023-05-14 12:56:28大
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return true-date1大于date2, false-date1小于date2
     */
    public static boolean isAfter(LocalDateTime date1, LocalDateTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.isAfter(date2);
    }

    /**
     * 比较两个日期是否相等
     * 如：2023-05-14 12:56:29 和 2023-05-14 12:56:29 两个日期相等
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return true-date1等于date2,false-date1不等于date2
     */
    public static boolean isEqual(LocalDateTime date1, LocalDateTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.isEqual(date2);
    }

    /**
     * 日期大小比较
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return 1:date1&gt;date2、0:date1=date2 -1:date1&lt;date2
     */
    public static int compareTo(LocalDate date1, LocalDate date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.compareTo(date2);
    }

    /**
     * 日期大小比较
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return 1:date1&gt;date2、0:date1=date2 -1:date1&lt;date2
     */
    public static int compareTo(LocalTime date1, LocalTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return date1.compareTo(date2);
    }

    /**
     * Duration对象类型比较大小
     *
     * @param duration1 日期对象
     * @param duration2 日期对象
     * @return 1:duration1&gt;duration2，0:duration1=duration2，-1:duration1&lt;duration2
     */
    public static int compareTo(Duration duration1, Duration duration2) {
        Asserts.illegalArgument(duration1);
        Asserts.illegalArgument(duration2);
        return duration1.compareTo(duration2);
    }

    /**
     * Instant对象类型比较大小
     *
     * @param instant1 日期对象
     * @param instant2 日期对象
     * @return 1：instant1&gt;instant2，0：instant1=instant2，-1：instant1&lt;instant2
     */
    public static int compareTo(Instant instant1, Instant instant2) {
        Asserts.illegalArgument(instant1);
        Asserts.illegalArgument(instant2);
        return instant1.compareTo(instant2);
    }

    /**
     * 获取指定日期的月份的第一天
     *
     * @param localDateTime 日期
     * @return 获取指定日期对应的第一天的日期对象
     */
    public static LocalDate firstDayOfMonth(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
    }

    /**
     * 获取指定日期的月份的第一天
     *
     * @param localDateTime 日期
     * @param month         向前推 month&gt;0 向后推&lt;0
     * @return 第一天
     */
    public static LocalDate firstDayOfMonth(LocalDateTime localDateTime, int month) {
        Asserts.illegalArgument(localDateTime);
        if (month == 0) {
            return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        } else if (month < 0) {
            return localDateTime.minusMonths(-month).with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        } else {
            return localDateTime.plusMonths(month).with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        }
    }

    /**
     * 获取指定日期的月份的最后一天
     *
     * @param localDateTime 日期
     * @return 最后一天的日期对象
     */
    public static LocalDate lastDayOfMonth(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
    }

    /**
     * 获取指定日期的月份的最后一天
     *
     * @param localDateTime 日期
     * @param month         向前推 month&gt;0 向后推&lt;0
     * @return 一个月的最后一天日期对象
     */
    public static LocalDate lastDayOfMonth(LocalDateTime localDateTime, int month) {
        Asserts.illegalArgument(localDateTime);
        if (month == 0) {
            return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        } else if (month < 0) {
            return localDateTime.minusMonths(-month).with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        } else {
            return localDateTime.plusMonths(month).with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        }
    }

    /**
     * 获取日期所在月份剩余的天数
     *
     * @param localDateTime 日期
     * @return 指定日期所在月份剩余天数
     */
    public static int getRemainDayOfMonth(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        LocalDateTime lastDayOfMonth = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return lastDayOfMonth.getDayOfMonth() - localDateTime.getDayOfMonth();
    }

    /**
     * 获取指定日期所在年份剩余的天数
     *
     * @param localDateTime 日期
     * @return 指定日期所在年剩余天数
     */
    public static int getRemainDayOfYear(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        LocalDateTime lastDayOfYear = localDateTime.with(TemporalAdjusters.lastDayOfYear());
        return lastDayOfYear.getDayOfYear() - localDateTime.getDayOfYear();
    }

    /**
     * 计算两个日期的时间间隔，精度是秒、纳秒
     * ---------------------------------------------------------------
     * 格式转换
     * Duration fromChar1 = Duration.parse("P1DT1H10M10.5S");
     * Duration fromChar2 = Duration.parse("PT10M");
     * 采用ISO-8601时间格式。格式为：PnYnMnDTnHnMnS   （n为个数）
     * <p>
     * 例如：P1Y2M10DT2H30M15.03S
     * P：开始标记
     * 1Y：一年
     * 2M：两个月
     * 10D：十天
     * T：日期和时间的分割标记
     * 2H：两个小时
     * 30M：三十分钟
     * 15S：15.02秒
     * ---------------------------------------------------------------
     *
     * @param date1, 开始日期
     * @param date2  结束日期
     * @return date1-date2&gt;0 返回正数，否则返回负数
     */
    public static Duration between(Temporal date1, Temporal date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return Duration.between(date2, date1);
    }

    /**
     * 计算两个日期的时间间隔，精度为年 月 日
     * ---------------------------------------------------------------
     * 格式转换：
     * "P2Y"             -- Period.ofYears(2)
     * "P3M"             -- Period.ofMonths(3)
     * "P4W"             -- Period.ofWeeks(4)
     * "P5D"             -- Period.ofDays(5)
     * "P1Y2M3D"         -- Period.of(1, 2, 3)
     * "P1Y2M3W4D"       -- Period.of(1, 2, 25)
     * "P-1Y2M"          -- Period.of(-1, 2, 0)
     * "-P1Y2M"          -- Period.of(-1, -2, 0)
     * P：开始符，表示period（即：表示年月日）；
     * Y：year；
     * M：month；
     * W：week；
     * D：day
     * ---------------------------------------------------------------
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return ddate1-date2&gt;0 返回正数，否则返回负数
     */
    public static Period between(LocalDate date1, LocalDate date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return Period.between(date2, date1);
    }

    /**
     * 两个对象相减得到毫秒
     *
     * @param instant1 时间对象
     * @param instant2 时间对象
     * @return 毫秒
     */
    public static long minusMillis(Instant instant1, Instant instant2) {
        Asserts.illegalArgument(instant1);
        Asserts.illegalArgument(instant2);
        return instant1.minusMillis(instant2.toEpochMilli()).toEpochMilli();
    }

    /**
     * 判定指定的年份是否是闰年
     * -------------------------------------------------------------------
     * 闰年是指公历中含有366天的年份，通常是每4年一次，但有例外。闰年的2月份有29天，而平年只有28天。在公历中，从公元前45年到公元3200年，闰年的规则为：
     * 能被4整除但不能被100整除的年份是闰年（如2004年就是闰年）；
     * 能被400整除的年份也是闰年（如2000年是闰年，1900年不是闰年）；
     * 闰年的出现是为了弥补平年中一年中多出来的约0.242199天（即365.242199天）的差距。
     * -------------------------------------------------------------------
     *
     * @param year 年份
     * @return true-是，false-否
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }

}
