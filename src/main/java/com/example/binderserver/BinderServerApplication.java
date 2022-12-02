package com.example.binderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class BinderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinderServerApplication.class, args);
	}

}
