package com.test.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * {@link LocalDateTime}工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class LocalDateTimes {

    /**
     * 将LocalDate转换为LocalDateTime
     *
     * @param localDate 日期对象
     * @return LocalDateTime对象
     */
    public static LocalDateTime from(LocalDate localDate) {
        return from(localDate, ZoneId.systemDefault());
    }

    /**
     * 将LocalDate转换为LocalDateTime
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
     * @param localDate 日期对象
     * @param zoneId    时区
     * @return LocalDateTime对象
     */
    public static LocalDateTime from(LocalDate localDate, ZoneId zoneId) {
        Asserts.illegalArgument(localDate);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    /**
     * 将Date数据类型转换为LocalDateTime
     *
     * @param date 日期对象
     * @return 转换后的LocalDateTime对象
     */
    public static LocalDateTime from(Date date) {
        return from(date, ZoneId.systemDefault());
    }

    /**
     * 将Date数据类型转换为LocalDateTime
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
     * @param date   日期对象
     * @param zoneId 时区
     * @return 转换后的LocalDateTime对象
     */
    public static LocalDateTime from(Date date, ZoneId zoneId) {
        Asserts.illegalArgument(date);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return date.toInstant().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    /**
     * 将字符串日期转换为LocalDateTime对象
     *
     * @param str     字符串日期
     * @param pattern 格式
     * @return 日期对象
     */
    public static LocalDateTime from(String str, String pattern) {
        return from(str, pattern, ZoneId.systemDefault());
    }

    /**
     * 将字符串日期转换为LocalDateTime对象
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
     * @param str     字符串日期
     * @param pattern 格式
     * @param zoneId  时区
     * @return 日期对象
     */
    public static LocalDateTime from(String str, String pattern, ZoneId zoneId) {
        Asserts.illegalArgument(str);
        Asserts.illegalArgument(pattern);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern)).atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    /**
     * 将日期对象和时间对象拼接成一个日期对象
     *
     * @param date1 日期对象
     * @param date2 时间对象
     * @return 拼接后的时间对象
     */
    public static LocalDateTime from(LocalDate date1, LocalTime date2) {
        return from(date1, date2, ZoneId.systemDefault());
    }

    /**
     * 将日期对象和时间对象拼接成一个日期对象
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
     * @param date1  日期对象
     * @param date2  时间对象
     * @param zoneId 时区
     * @return 拼接后的时间对象
     */
    public static LocalDateTime from(LocalDate date1, LocalTime date2, ZoneId zoneId) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return LocalDateTime.of(date1, date2).atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    /**
     * 将时间转换为另一个时区的时间对象
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
     * @param date1  日期对象
     * @param zoneId 时区对象
     * @return 转换后的日期对象
     */
    public static LocalDateTime from(LocalDateTime date1, ZoneId zoneId) {
        Asserts.illegalArgument(date1);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return date1.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    /**
     * 将时间戳转换为日期对象
     *
     * @param milliseconds 时间戳，毫秒，如： System.currentTimeMillis()  1685353612112L
     * @return 日期对象
     */
    public static LocalDateTime from(long milliseconds) {
        return from(milliseconds, ZoneId.systemDefault());
    }

    /**
     * 将时间戳转换为日期对象
     * --------------------------------------------------------------------------------------------
     * 示例说明：
     * Instant instant = Instant.ofEpochMilli(0);
     * LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
     * 获取到的是世界标准时间，即格林威治时间；因为我们是在东八区，需加上八个小时，所以格式化后的时间是1970-01-01 08:00:00
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
     * @param milliseconds 时间戳，毫秒，如： System.currentTimeMillis()  1685353612112L
     * @param zoneId       时区
     * @return 日期对象
     */
    public static LocalDateTime from(long milliseconds, ZoneId zoneId) {
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        Instant instant = Instant.ofEpochMilli(milliseconds);
        return LocalDateTime.ofInstant(instant, zoneId);
    }
    //----------------------------------------------------------------combine----------------------------------------------------------------------------

    /**
     * 将日期和时间类型拼接成 LocalDateTime对象
     *
     * @param date1 日期
     * @param date2 时间
     * @return 拼接后的时间对象
     */
    public static LocalDateTime combine(LocalDate date1, LocalTime date2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(date2);
        return LocalDateTime.of(date1, date2);
    }

    /**
     * 将日期和时间类型拼接成 LocalDateTime对象
     *
     * @param date1    日期
     * @param pattern1 日期date1的格式类型
     * @param date2    时间
     * @param pattern2 日期date2的格式类型
     * @return 拼接后的时间对象
     */
    public static LocalDateTime combine(String date1, String pattern1, String date2, String pattern2) {
        Asserts.illegalArgument(date1);
        Asserts.illegalArgument(pattern1);
        Asserts.illegalArgument(date2);
        Asserts.illegalArgument(pattern2);
        return LocalDateTime.of(LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern1)),
                LocalTime.parse(date2, DateTimeFormatter.ofPattern(pattern2)));
    }

}
