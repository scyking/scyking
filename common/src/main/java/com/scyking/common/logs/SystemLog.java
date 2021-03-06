package com.scyking.common.logs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志切面注解
 *
 * @author scyking
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {

    SystemLogTypeEnum type() default SystemLogTypeEnum.OTHER;

    SystemLogModuleEnum module() default SystemLogModuleEnum.OTHER;

}

