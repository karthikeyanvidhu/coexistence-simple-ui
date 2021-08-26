package com.example.accessingdatamongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping(value = "/inbound")
	public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producer.sendMessage(message);
		return "message posted to test-inbound topic";
	}

	@GetMapping(value = "/outbound")
	public String sendMessageToMqKafkaTopic(@RequestParam("message") String message) {
		this.producer.sendMessagetoMq(message);
		return "message posted to test-outbound topic";
	}

}
