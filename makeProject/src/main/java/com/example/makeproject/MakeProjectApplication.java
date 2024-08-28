package com.example.makeproject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableBatchProcessing // Batch use
//@SpringBootApplication
public class MakeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakeProjectApplication.class, args);
    }

}
