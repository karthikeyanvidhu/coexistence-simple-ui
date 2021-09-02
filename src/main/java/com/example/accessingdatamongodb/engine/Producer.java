package com.example.accessingdatamongodb.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "test-outbound";
    private static final String TOPIC2= "test-outbound";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("#### ->users Producing message -> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}

	public void sendMessagetoMq(String message) {
		logger.info(String.format("#### ->mq-kafka-topic Producing message -> %s", message));
		this.kafkaTemplate.send(TOPIC2, message);
	}

}
