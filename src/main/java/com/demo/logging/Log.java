package com.demo.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // Can be applied to methods only
@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
public @interface Log {
    String value() default "";  // Optional description
    boolean logParameters() default true;  // Whether to log method parameters
    boolean logExecutionTime() default false;  // Whether to log execution time
}