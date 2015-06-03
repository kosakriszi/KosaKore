package com.kosakorner.kosakore.api.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation for an executable command.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String[] aliases();

    String desc() default "";

    String usage() default "";

    String perm() default "";

    boolean allowConsole() default false;

    boolean allowCommandBlock() default false;

}
