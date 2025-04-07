package com.bezkoder.spring.mssql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@Cacheable

public class SpringBootSqlServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSqlServerApplication.class, args);
	}

}
