package com.epam.spring.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class MethodLogger {

  private final Logger logger = Logger.getLogger(MethodLogger.class);

    @Around("within(com.epam.spring.service.impl.*)")
  public Object around(ProceedingJoinPoint point) {
    long start = System.currentTimeMillis();
    Object result = null;
    try {
      result = point.proceed();
    } catch (Throwable throwable) {
      logger.debug("Method " + point.getSignature()
                                    .toShortString() + " with args " + " " + Arrays.toString(
          point.getArgs()), throwable);
    }

    logger.debug("Method " + point.getSignature()
                                  .toShortString() + " with args " + " " + Arrays.toString(
        point.getArgs()) + " spent time " + (System.currentTimeMillis() - start));
    return result;
  }
}