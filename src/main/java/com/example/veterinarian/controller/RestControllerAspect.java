package com.example.veterinarian.controller;

import com.example.veterinarian.security.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RestControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);

    @Around("@annotation(log)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String methodName = joinPoint.getSignature().getName();
        Map<String, Object> parameters = obtainParameters(joinPoint);
        logger.info("{} started with parameters: {}", methodName, parameters);
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            logger.info("{} failed with exception message: {}", methodName, e.getMessage());
            throw e;
        }
        logger.info("{} finished with return value: {}", methodName, proceed);
        return proceed;
    }

    private Map<String, Object> obtainParameters(ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = new HashMap<>();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        for (int i=0; i < parameterNames.length && i < parameterValues.length; i++) {
            parameters.put(parameterNames[i], parameterValues[i]);
        }
        return parameters;
    }
}
