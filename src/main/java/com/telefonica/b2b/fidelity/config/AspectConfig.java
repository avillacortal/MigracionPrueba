package com.telefonica.b2b.fidelity.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {

    @Pointcut("execution(public * pe.com.telefonica.b2b.fidelity.rest..*.*(..))")
    public void logServiceMethod() {
    }

    @Pointcut("execution(public * pe.com.telefonica.b2b.fidelity.service..*.*(..))")
    public void logBusinessMethod() {
    }

}
