package com.microservice.user.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class UserAspect {
	
	@Before("execution((public * com.microservice.user.controller.*.*(..))")
	public void logBeforeAllControllerMethods(JoinPoint joinPoint) {
		log.info("Inside saveUser method of UserService");
	}
	
	@Before("execution((public * com.microservice.user.controller.*.*(..))")
	public void logBeforeAllServiceMethods(JoinPoint joinPoint) {
		log.info("Inside saveUser method of UserController");
	}
}
