package com.tiagohenriqueramos.autocenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tiagohenriqueramos.autocenter")
public class AutoCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoCenterApplication.class, args);
	}

}
