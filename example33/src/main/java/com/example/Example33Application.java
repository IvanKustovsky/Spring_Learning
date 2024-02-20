package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages= "com.example.proxy")
public class Example33Application {

	public static void main(String[] args) {
		SpringApplication.run(Example33Application.class, args);
	}

}
