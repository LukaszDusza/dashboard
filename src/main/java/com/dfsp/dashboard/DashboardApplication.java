package com.dfsp.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DashboardApplication {

	//	static public final String PATH = "http://89.67.4.242:11780/dashboard-rest/";

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(DashboardApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}
}
