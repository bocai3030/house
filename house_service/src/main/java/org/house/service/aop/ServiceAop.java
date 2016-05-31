package org.house.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ServiceAop {
	@Around("execution(* org.house.service.spider.ShellSpiderController.*(..))")
	public void aopOfShellSpiderController(final ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			joinPoint.proceed();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}