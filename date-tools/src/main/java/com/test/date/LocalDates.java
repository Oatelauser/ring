package com.test.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 概要描述
 * <p>
 * 详细描述
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class LocalDates {

    /**
     * 将Date数据类型转换为LocalDate
     *
     * @param date 日期对象
     * @return 转换后的LocalDate对象
     */
    public static LocalDate from(Date date) {
        return from(date, ZoneId.systemDefault());
    }

    /**
     * 将Date数据类型转换为LocalDate
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
     * @return 转换后的LocalDate对象
     */
    public static LocalDate from(Date date, ZoneId zoneId) {
        Asserts.illegalArgument(date);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return date.toInstant().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDate();
    }

    /**
     * 将LocalDateTime 转 LocalDate
     *
     * @param localDateTime 日期对象
     * @return LocalDate日期对象
     */
    public static LocalDate from(LocalDateTime localDateTime) {
        return from(localDateTime, ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime 转 LocalDate
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
     * @param localDateTime 日期对象
     * @param zoneId        时区
     * @return LocalDate日期对象
     */
    public static LocalDate from(LocalDateTime localDateTime, ZoneId zoneId) {
        Asserts.illegalArgument(localDateTime);
        zoneId = Asserts.requireElseGet(zoneId, ZoneId.systemDefault());
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDate();
    }

    /**
     * 将字符串日期转换为LocalDate对象
     *
     * @param str     字符串日期
     * @param pattern 格式
     * @return 日期对象
     */
    public static LocalDate from(String str, String pattern) {
        return from(str, pattern, ZoneId.systemDefault());
    }

    /**
     * 将字符串日期转换为LocalDate对象
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
    public static LocalDate from(String str, String pattern, ZoneId zoneId) {
        Asserts.illegalArgument(str);
        Asserts.illegalArgument(pattern);
        if (zoneId == null) {
            zoneId = ZoneId.systemDefault();
        }
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern))
                .atStartOfDay().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDate();
    }

}
