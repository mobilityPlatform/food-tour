package com.gastroventure.lion.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * AutoBus RestAPI Response annotation
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonResponse {
    @AliasFor("apiId")
    String value() default "";

    /**
     * apiId value
     *
     * @return
     */
    @AliasFor("value")
    String apiId() default "";

//    AUTOBUS_API apiEnum() default AUTOBUS_API.DEFAULT;
}
