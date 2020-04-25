package com.apbdoo.hrm.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
	@Before("execution(* com.apbdoo.hrm.service.*.*(..))")
	public void logTheApelCallBeforeExecution(JoinPoint joinPoint)
	{
		Object[] args = joinPoint.getArgs();
		log.info("Spring AOP Logger: Method " + joinPoint.getSignature()
			+ " was called with the following arguments: ");
		for (Object arg : args)
		{
			log.info(arg.toString());
		}
	}
	
	@AfterReturning(
		pointcut = "execution(* com.apbdoo.hrm.service.*.*(..))",
		returning = "result"
	)
	public void logTheApelCallAfterExecution(JoinPoint joinPoint, Object result)
	{
		log.info("Spring AOP Logger: Method " + joinPoint.getSignature() + " has returned the value: " + result);
	}
}
