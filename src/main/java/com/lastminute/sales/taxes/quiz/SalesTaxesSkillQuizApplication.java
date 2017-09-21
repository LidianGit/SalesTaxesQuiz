package com.lastminute.sales.taxes.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SalesTaxesSkillQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesTaxesSkillQuizApplication.class, args);
	}

}
