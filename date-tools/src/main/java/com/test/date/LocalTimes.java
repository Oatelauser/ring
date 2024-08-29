package com.test.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * {@link LocalTime}工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class LocalTimes {

    /**
     * 将Date数据类型转换为LocalDate
     *
     * @param date 日期对象
     * @return 转换后的LocalDate对象
     */
    public static LocalTime from(Date date) {
        Asserts.illegalArgument(date);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * 将LocalDateTime转 LocalTime对象
     *
     * @param localDateTime 日期对象
     * @return LocalTime对象
     */
    public static LocalTime from(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        return localDateTime.toLocalTime();
    }

    /**
     * 将字符串日期转换为LocalDate对象
     *
     * @param str     字符串日期
     * @param pattern 格式
     * @return 日期对象
     */
    public static LocalTime from(String str, String pattern) {
        Asserts.illegalArgument(str);
        Asserts.illegalArgument(pattern);
        return LocalTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

}
