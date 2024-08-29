package com.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * {@link Date}工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class Dates {

    /**
     * 字符串日期格式化
     *
     * @param str     字符串日期
     * @param pattern 日期格式
     * @return 格式化后的日期
     */
    public static Date from(String str, String pattern) {
        try {
            Asserts.illegalArgument(str);
            Asserts.illegalArgument(pattern);
            DateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数");
        }
    }

    /**
     * 将LocalDateTime转为Date
     *
     * @param localDateTime 日期类型
     * @return Date日期对象
     */
    public static Date from(LocalDateTime localDateTime) {
        Asserts.illegalArgument(localDateTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将LocalDateTime转为Date
     *
     * @param localDate 日期类型
     * @return Date日期对象
     */
    public static Date from(LocalDate localDate) {
        Asserts.illegalArgument(localDate);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将毫秒转换为日期对象
     * Date date = new Date(0) 获取到的世界标准时间，即格林威治时间；
     * 如果要转换为中国时间，因为我们是在东八区，需加上八个小时，所以是1970-01-01 08:00:00
     *
     * @param milliseconds 毫秒，如：System.currentTimeMillis()  1685353612112L
     * @return 日期对象
     */
    public static Date from(long milliseconds) {
        return new Date(milliseconds);
    }

}
