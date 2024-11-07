package com.lucas.bomkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BomkeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BomkeyApplication.class, args);
	}

}
