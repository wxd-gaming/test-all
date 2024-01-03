package org.wxd.mmo.script.gamesr.gm;

import java.lang.annotation.*;

/**
 * gm方法注解
 */
@Documented
@Target({ElementType.METHOD/*方法*/})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gm {

    /** 分组 默认空 */
    String group() default "";

    int gmLv() default 1;

    /** 默认 99999 */
    int sort() default 9999;

    String defaultValue() default "";

    String comment() default "";

}

