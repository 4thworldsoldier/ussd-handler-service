package com.isw.ussd.handler.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class UssdHandlerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UssdHandlerServiceApplication.class, args);
	}

}
