package com.example.accessingdatamongodb.engine;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.accessingdatamongodb.repository.ListenInfo;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private ListenInfoRepository infoRepo;
	
	@KafkaListener(topics = "mq-inbound", groupId = "group_id")
	public void consumed(String message) throws IOException {
		ListenInfo info=new ListenInfo();
		info.setInfo(message);
		info.setTimeStamp(Instant.now().toString());
		infoRepo.save(info);
		logger.info(String.format("#### -> mq-inbound Consumed message -> %s", message));
	}

	@KafkaListener(topics = "mq-outbound", groupId = "group_id")
	public void consume(String message) throws IOException {
		ListenInfo info=new ListenInfo();
		info.setInfo(message);
		info.setTimeStamp(Instant.now().toString());
		infoRepo.save(info);		
		logger.info(String.format("#### -> mq-outbound Consumed message -> %s", message));		
	}

}
