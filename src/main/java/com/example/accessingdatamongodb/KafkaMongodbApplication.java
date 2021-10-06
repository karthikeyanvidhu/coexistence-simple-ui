package com.example.accessingdatamongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.accessingdatamongodb.repository.CustomerInfo;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@SpringBootApplication
public class KafkaMongodbApplication implements CommandLineRunner {


	
	@Autowired
	private ListenInfoRepository mongoMsgRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaMongodbApplication.class, args);
	}
 

	@Override
	public void run(String... args) throws Exception {
		//kafkaMsgRepository.deleteAll();
		// fetch all Messages
		System.out.println("All KAFKA Messages found with findAll():");
		System.out.println("-------------------------------");
		List<CustomerInfo> li=mongoMsgRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile("karthik","lastname","0012345130","8880098");
		for (CustomerInfo info : li) {
			System.out.println(info);
		}
	}
	

}
