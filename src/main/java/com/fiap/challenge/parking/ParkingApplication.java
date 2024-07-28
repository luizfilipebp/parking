package com.fiap.challenge.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

}
