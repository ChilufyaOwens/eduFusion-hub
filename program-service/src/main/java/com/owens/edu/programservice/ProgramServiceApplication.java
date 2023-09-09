package com.owens.edu.programservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProgramServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProgramServiceApplication.class, args);
    }
}
