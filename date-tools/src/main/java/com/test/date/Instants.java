package com.test.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * {@link Instant}工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
public abstract class Instants {

    /**
     * ISO-8601 格式的日期时间字符串
     * String isoDateTimeString = "2023-01-01T12:00:00Z";
     *
     * @param text 日期字符串
     * @return 日期对象
     */
    public static Instant from(CharSequence text) {
        Asserts.illegalArgument(text);
        // 解析字符串为 Instant 对象
        return Instant.parse(text);
    }

    /**
     * 将LocalDateTime对象转换为Instant对象
     *
     * @param localDateTime 日期对象
     * @return 日期对象
     */
    public static Instant from(LocalDateTime localDateTime) {
        return from(localDateTime, ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime对象转换为Instant对象
     *
     * @param localDateTime 日期对象
     * @param zoneId        时区ID
     * @return 日期对象
     */
    public static Instant from(LocalDateTime localDateTime, ZoneId zoneId) {
        Asserts.illegalArgument(localDateTime);
        Asserts.illegalArgument(zoneId);
        return localDateTime.atZone(zoneId).toInstant();
    }

}
