package com.si.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = {"com.si.practice.infrature.mysql.mapper"})
@SpringBootApplication
@EnableScheduling
@Slf4j
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);

//		log.info("hello world. \thello ty");
	}

}
