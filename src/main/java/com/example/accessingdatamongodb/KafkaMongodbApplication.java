package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.accessingdatamongodb.repository.Customer;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import com.example.accessingdatamongodb.repository.ListenInfo;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@SpringBootApplication
public class KafkaMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private ListenInfoRepository kafkaMsgRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaMongodbApplication.class, args);
	}
 

	@Override
	public void run(String... args) throws Exception {
		
		kafkaMsgRepository.save(new ListenInfo("timeStamp:2021-08-20T07:32:16.397777100Z","{\"sales_date\":\"2016-01-01\",\"sales_time\":\"12:00:00\",\"organisation_id\":\"540\",\"department_id\":\"5401\",\"salesperson_id\":\"540001\",\"customer_id\":\"1234\"}"));
		repository.deleteAll();
		
		// fetch all Messages
		System.out.println("All KAFKA Messages found with findAll():");
		System.out.println("-------------------------------");
		for (ListenInfo info : kafkaMsgRepository.findAll()) {
			System.out.println(info);
		}

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		repository.save(new Customer("Cat", "Smith"));
		repository.save(new Customer("Delta", "Smith"));
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

	}
	

}
