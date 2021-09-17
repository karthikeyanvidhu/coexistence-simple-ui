package com.example.accessingdatamongodb;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.accessingdatamongodb.repository.ListenInfo;
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
		mongoMsgRepository.save(new ListenInfo("001000723","ADD","0012345130","sachin","cric","1900-09-21","TES12","201","89765","8899877","8880098","sachin@ABCD.COM",Instant.now().toString()));

		// fetch all Messages
		System.out.println("All KAFKA Messages found with findAll():");
		System.out.println("-------------------------------");
		List<ListenInfo> li=mongoMsgRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile("sachin","lastname","0012345130","8880098");
		for (ListenInfo info : li) {
			System.out.println(info);
		}
	}
	

}
