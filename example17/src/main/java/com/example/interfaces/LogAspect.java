package com.example.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * The @LogAspect annotation is a custom annotation used for marking methods that should be intercepted
 * by a logging aspect. This annotation is designed to be retained at runtime (RetentionPolicy.RUNTIME),
 * meaning it will be accessible through reflection at runtime. It can be applied only to method declarations
 * (ElementType.METHOD), indicating that the associated method is a target for logging aspects.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAspect {
}
