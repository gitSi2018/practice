package com.si.practice.dbpractice;

import com.si.practice.dbpractice.common.order.OrderDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@MapperScan(basePackages = {"com.si.practice.dbpractice.infrature.mysql.mapper"})
@SpringBootApplication
@EnableScheduling
@Slf4j
public class DBPracticeApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DBPracticeApplication.class, args);

//		log.info("hello world. \thello ty");
		sort();
	}


	public static void sort(){

		List<TestDO> testDOS = init(20);
		testDOS = testDOS.stream().
				sorted(Comparator.comparing(TestDO::getStatus).
						thenComparing((x, y) -> y.getDistance().compareTo(x.getDistance()))).
				collect(Collectors.toList());
		log.info("testDOS:{}", testDOS);

	}

	private static List<TestDO> init(int size){

		List<TestDO> testDOS = new ArrayList<>(size);
		for (int i = 0; i < size; i++){

			TestDO testDO = new TestDO();
			int status = (int)(Math.random() * 5) + 1;
			double distance = Math.random() * 10;
			testDO.setDistance(distance);
			testDO.setStatus(status);
			testDOS.add(testDO);
		}
		log.info("init testDOS:{}", testDOS);
		return testDOS;
	}


}
@Data
class TestDO{

	private Integer status;

	private Double distance;

	@Override
	public String toString(){

		return "[" + status + "," + distance + "]\n";

	}
}
