package com.jobifyProject.jobify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class JobifyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobifyProjectApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.jobify.jobifyProject"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Jobify API",
				"This is the official API documentation of Jobify.",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Vasile Gabriel", "https://www.linkedin.com/in/vasile-gabriel-marian-11155019a/", "gabriel_marian22@yahoo.com"),
				"API License",
				"google.com",
				Collections.emptyList());
	}
}
