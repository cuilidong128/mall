package com.mall.common.annotation;


import java.lang.annotation.*;

/**
 *  防止表单重复提交过滤
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DuplicateFilter {

    boolean check() default true;
}