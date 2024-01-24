package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/*AOP - Aspect Oriented Programming
 * AOP Jargon:
 * Aspect -> WHAT code or logic we want the Spring to execute when you call a specific method. This is called as Aspect.
 * Advice -> WHEN the Spring need to execute the given Aspect. For ex.(Before, After, Around). This is called as Advice.
 * Pointcut -> WHICH method inside App that framework needs to intercept and execute the given Aspect. This is Pointcut.
 *
 * Join Point which defines the event that triggers the execution of an aspect.
 * Inside Spring, this event is always a method call.
 *
 * Types of Advices: @Before, @After, @Around (combination of @Before and @After), @AfterThrowing, @AfterReturning
 *
 * @Order(n) indicates the order in which this Aspect should be executed among multiple Aspects.
 * In this case, with @Order(2), this Aspect is assigned a priority of 2, meaning it will be executed
 * after Aspects with lower order values and before those with higher order values.
 * */
@Aspect
@Component
@Order(1)
public class VehicleStartCheckAspect {

    private final Logger logger = Logger.getLogger(VehicleStartCheckAspect.class.getName());

    @Before("execution(* com.example.services.*.*(..)) && args(vehicleStarted,..)")
    public void checkVehicleStarted(JoinPoint joinPoint, boolean vehicleStarted) throws Throwable {
        if(!vehicleStarted){
            throw new RuntimeException("Vehicle not started");
        }
    }
}