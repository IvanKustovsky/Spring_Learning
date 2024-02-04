package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.repository") // The following annotations is optional, as Spring Boot can automatically
@EntityScan("com.example.model") //detect repositories in the same package or sub-packages as the main application class.
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EasySchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasySchoolApplication.class, args);
    }

}
