package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "com.ssafy.happyhouse.mapper"})
public class HappyhouseFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyhouseFinalApplication.class, args);
	}

}
