package com.kosakorner.kosakore.api.module;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Module {

    String id();

    String name();

    String version() default "unknown";

    String dependencies() default "";

}
