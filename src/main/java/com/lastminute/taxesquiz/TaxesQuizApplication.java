package com.lastminute.taxesquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@PropertySource(TaxesQuizApplication.configLocation)
public class TaxesQuizApplication {

	public static final String configLocation = "file:CONFDIR/config/external.properties";

	public static void main(String[] args) {
		SpringApplication.run(TaxesQuizApplication.class, args);
	}

}
