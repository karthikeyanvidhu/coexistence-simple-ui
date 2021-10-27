package com.example.accessingdatamongodb;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InsuranceUI implements CommandLineRunner {



	
	public static void main(String[] args) {
		SpringApplication.run(InsuranceUI.class, args);
	}
 

	@Override
	public void run(String... args) throws Exception {
		//kafkaMsgRepository.deleteAll();
		// fetch all Messages
		System.out.println("All KAFKA Messages found with findAll():");
		System.out.println("-------------------------------");
	}
	

}
