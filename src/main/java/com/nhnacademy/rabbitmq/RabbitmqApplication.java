package com.nhnacademy.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class RabbitmqApplication{
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}


}
