package com.demo.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(Log)")  // Intercepts all methods annotated with @Log
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);

        String methodName = signature.getDeclaringType().getSimpleName()
                + "." + signature.getName();

        // Log method entry
        logger.info("Entering method: {}", methodName);

        if (logAnnotation.logParameters()) {
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                logger.debug("Method parameters: {}", Arrays.toString(args));
            }
        }

        StopWatch stopWatch = null;
        if (logAnnotation.logExecutionTime()) {
            stopWatch = new StopWatch();
            stopWatch.start();
        }

        try {
            // Execute the method
            Object result = joinPoint.proceed();

            // Log method exit
            if (logAnnotation.logExecutionTime()) {
                stopWatch.stop();
                logger.info("Exiting method: {} | Execution time: {} ms",
                        methodName, stopWatch.getTotalTimeMillis());
            } else {
                logger.info("Exiting method: {}", methodName);
            }

            return result;
        } catch (Exception e) {
            logger.error("Exception in method: {}", methodName, e);
            throw e;
        }
    }
}
