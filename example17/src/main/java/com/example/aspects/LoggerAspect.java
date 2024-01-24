package com.example.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
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
@Order(2)
public class LoggerAspect {

    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* com.example.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        // Before requirements
        logger.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();

        // Invoking actual method
        Object result = joinPoint.proceed();

        // After execution actual method logic
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        logger.info("Time took to execute the method : "+timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");

        return result;
    }

    @Around("@annotation(com.example.interfaces.LogAspect)") // using custom annotation
    public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        // Before requirements
        logger.info(joinPoint.toString() + " method execution start");
        Instant start = Instant.now();

        // Invoking actual method
        Object result = joinPoint.proceed();

        // After execution actual method logic
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute the method : "+ timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");

        return result;
    }

    @AfterThrowing(value = "execution(* com.example.services.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE,joinPoint.getSignature()+ " An exception thrown with the help of" +
                " @AfterThrowing which happened due to : " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.example.services.*.*(..))",returning = "retVal")
    public void logStatus(JoinPoint joinPoint,Object retVal) {
        logger.log(Level.INFO,joinPoint.getSignature()+ " Method successfully processed with the status " +
                retVal.toString());
    }
}