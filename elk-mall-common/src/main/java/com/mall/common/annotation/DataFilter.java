package com.mall.common.annotation;

import java.lang.annotation.*;

/**
 *   数据权限过滤注解 kingMicro
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**  表的别名 */
    String tableAlias() default  "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;

    /**  true：数据权限范围为1级。查到1级下所有数据、一般为sass企业级数据权限 */
    boolean top() default false;
}
