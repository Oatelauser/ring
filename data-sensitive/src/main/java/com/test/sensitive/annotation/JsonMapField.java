package com.test.sensitive.annotation;

import com.test.sensitive.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义jackson注解，标注在属性上，实例如下：
 * <pre>
 *     {@code
 *     @JsonMapField(value = {"password", "username"}, types = {SensitiveType.DEFAULT, SensitiveType.USERNAME})
 *     private Map<String, String> params = new HashMap<>();
 *     }
 * </pre>
 * 1. Map的key必须为字符串，否则忽略
 * 2. Map的value值必须为字符串或对象类型；
 *
 * @author Emily
 * @since :  Created in 2022/7/19 5:22 下午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonMapField {
    /**
     * 要隐藏的参数key名称
     *
     * @return 复杂类型字段名
     */
    String[] value() default {};

    /**
     * 脱敏类型，见枚举类型{@link SensitiveType}
     *
     * @return 脱敏类型
     */
    SensitiveType[] types() default {};
}
