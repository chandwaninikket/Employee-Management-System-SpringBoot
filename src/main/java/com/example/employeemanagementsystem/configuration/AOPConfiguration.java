package com.example.employeemanagementsystem.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfiguration {
    private static final Logger LOG = LogManager.getLogger(AOPConfiguration.class);

    @Pointcut("within(com.example.employeemanagementsystem..*)")
    private void everythingInMyApplication() {}

    @Before("com.example.employeemanagementsystem.configuration.AOPConfiguration.everythingInMyApplication()")
    public void logMethodName(JoinPoint joinPoint) {
        LOG.info("Called {}", joinPoint.getTarget().getClass() + joinPoint.getSignature().getName());
        LOG.info("Called method {}",   joinPoint.getSignature().getName());
    }
}
