package com.lucas.eazybankboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity //FIXME: (debug = true) Only For Dev
public class EazybankBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazybankBootApplication.class, args);
    }

}
