package com.example.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.userservice.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan
public class UserserviceApplication {
	private static final Logger logger = LoggerFactory.getLogger(UserserviceApplication.class);

	public static void main(String[] args) {
		logger.info("Starting UserserviceApplication...");
		SpringApplication.run(UserserviceApplication.class, args);
		logger.info("UserserviceApplication started.");
	}
}

