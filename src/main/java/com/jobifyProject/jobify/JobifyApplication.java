package com.jobifyProject.jobify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class JobifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobifyApplication.class, args);
	}

}
