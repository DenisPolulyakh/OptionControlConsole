package com.traderobot.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.traderobot.www.repository"})
@EntityScan(basePackages = {"com.traderobot.www"})
public class OptionControlConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptionControlConsoleApplication.class, args);
	}

}
