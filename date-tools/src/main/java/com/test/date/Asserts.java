package com.test.date;

/**
 * 断言工具类
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2024-08-29
 * @since 1.0
 */
class Asserts {

    /**
     * 判定字符串是否为null或者空串
     *
     * @param str 字符
     */
    static void illegalArgument(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("非法参数");
        }
    }

    /**
     * 判定对象是否为null
     *
     * @param obj 对象
     */
    static void illegalArgument(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("非法参数");
        }
    }

    /**
     * 判定字符串是否为null或者空串，如果是，则返回defaultValue,否则原值返回
     *
     * @param str          字符串对象
     * @param defaultValue 默认值
     * @return 结果值
     */
    static String requireElseGet(String str, String defaultValue) {
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        return str;
    }

    /**
     * 判定对象是否为null，如果是则返回defaultValue，否则原值返回
     *
     * @param t            值对象
     * @param defaultValue 默认值
     * @param <T>          对象类型
     * @return 结果
     */
    static <T> T requireElseGet(T t, T defaultValue) {
        if (t == null) {
            return defaultValue;
        }
        return t;
    }
}

