package com.example.accessingdatamongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamongodb.engine.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	private final Producer producer;

	@Autowired
	KafkaController(Producer producer) {
		this.producer = producer;
	}
	
	@Value("${spring.kafka.topics}")
	private String TOPIC;
	
	@PostMapping(value = "/inbound")
	public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
		System.out.println("message posted to test-inbound topic::"+ TOPIC);
		this.producer.sendMessage(message,TOPIC);
		return "message posted to test-inbound topic";
	}

}
