package com.medilabosolutions.PatientReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.medilabosolutions.PatientReport")
public class PatientReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientReportApplication.class, args);
	}

}
