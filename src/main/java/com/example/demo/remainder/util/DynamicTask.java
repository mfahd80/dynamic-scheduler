package com.example.demo.remainder.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicTask {
    String cron() default "";

    long fixedDelay() default 0L;

    String notes() default "";

    String code();

    String name();
}