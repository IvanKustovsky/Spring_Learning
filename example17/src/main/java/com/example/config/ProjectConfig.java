package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/*
 * @EnableAspectJAutoProxy is used to enable support for AspectJ-style annotation-driven proxy creation.
 * This annotation allows the Spring container to automatically generate proxies for beans that are annotated with
 * @Aspect, making them AOP-enabled. It enables the AspectJ annotation support and ensures that Spring uses AspectJ
 * proxies for the annotated beans, allowing the execution of aspects (cross-cutting concerns) in the application.
 */
@Configuration
@ComponentScan(basePackages = {"com.example.implementation",
            "com.example.services", "com.example.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
