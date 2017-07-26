package com.ts.ccdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CcDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcDashboardApplication.class, args);
	}
}
