package com.web.desenvolvimento.edusphere;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdusphereApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty(	
				"spring.datasource.url",
				dotenv.get("DB_URL")
		);
		System.setProperty(
				"spring.datasource.username",
				dotenv.get("DB_USER")
		);
		System.setProperty(
				"spring.datasource.password",
				dotenv.get("DB_PASSWORD")
		);
		SpringApplication.run(EdusphereApplication.class, args);
	}
}
