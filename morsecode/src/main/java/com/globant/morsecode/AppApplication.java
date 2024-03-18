package com.globant.morsecode;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.globant.morsecode.GlobalApplicationContext.getBean;

@SpringBootApplication
public class AppApplication {

	@Autowired private GlobalApplicationContext globalApplicationContext;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
