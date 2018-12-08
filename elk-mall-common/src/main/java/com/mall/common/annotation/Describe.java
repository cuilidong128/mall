package com.mall.common.annotation;

import java.lang.annotation.*;

/**
 * 功能描述
 *
 * @author zhouhao
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Describe {
    String value();

    Class type() default Object.class;
}