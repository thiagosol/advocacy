package com.advocacy.advocacysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication(scanBasePackages = {
		"com.advocacy.advocacysystem.entrypoint.*",
		"com.advocacy.advocacysystem.core.*",
		"com.advocacy.advocacysystem.infrastructure.*"
})
public class AdvocacySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvocacySystemApplication.class, args);
	}

}
