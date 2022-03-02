package com.hunguyen.spring_app;

import com.hunguyen.spring_app.config.StorageProperties;
import com.hunguyen.spring_app.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}
@Bean
	CommandLineRunner init(StorageService storageService){
		return (arsg ->{
			storageService.init();
		});

	}

}
