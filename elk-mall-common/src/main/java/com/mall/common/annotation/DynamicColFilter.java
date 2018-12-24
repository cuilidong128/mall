package com.mall.common.annotation;

import java.lang.annotation.*;


/**
 * 动态列
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicColFilter {

    boolean status() default true;
}
