package io.github.miris.dnsresolver.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {}
    
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {}
    
    @Around("(serviceLayer() || controllerLayer())")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        log.debug("→ {}.{} started", className, methodName);
        
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            log.error("✕ {}.{} failed with exception: {}", className, methodName, e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            log.debug("← {}.{} completed in {}ms", className, methodName, (endTime - startTime));
            
            if (result != null) {
                log.trace("⟳ {}.{} returned: {}", className, methodName, result);
            }
        }
    }
}