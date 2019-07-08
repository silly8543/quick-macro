package com.github.silly8543.reptile.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脚本注解
 * Created by silly on 2019/3/11 10:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Script {

    /**
     * 值
     *
     * @return
     */
    String value();
}
