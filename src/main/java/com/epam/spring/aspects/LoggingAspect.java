package com.epam.spring.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Around("within(com.epam.spring.service.impl.*)")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            logger.debug("Method " + point.getSignature()
                    .toShortString() + " with args " + Arrays.toString(
                    point.getArgs()), throwable);
        }

        logger.debug("Method " + point.getSignature()
                .toShortString() + "| Spent time " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}