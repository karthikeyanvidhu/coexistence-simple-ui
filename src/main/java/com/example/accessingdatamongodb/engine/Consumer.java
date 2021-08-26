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
	
	/*@KafkaListener(topics = "mq-inbound", groupId = "group_id")
	public void consumed(String message) throws IOException {
		ListenInfo info=new ListenInfo();
		info.setInfo(message);
		info.setTimeStamp(Instant.now().toString());
		infoRepo.save(info);
		logger.info(String.format("#### -> mq-inbound Consumed message -> %s", message));
	}*/

	@KafkaListener(topics = "test-inbound", groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> test-inbound Consumed message -> %s", message));		
		//String customerRecord="123456788;ADD;001000721;ASHISH;TEST;1900-09-21;TEST1;201;89765;8899877;8880098;ABCD@ABCD.COM";
		String[] custInfoArray=message.split(";");
		ListenInfo info=new ListenInfo();
		info.setCorrelationId(custInfoArray[0]);
		info.setOperation(custInfoArray[1]);
		info.setCustomerId(custInfoArray[2]);
		info.setFirstName(custInfoArray[3]);
		info.setLastName(custInfoArray[4]);
		info.setDateOfBirth(custInfoArray[5]);
		info.setHouseName(custInfoArray[6]);
		info.setHouseNumber(custInfoArray[7]);
		info.setPostCode(custInfoArray[8]);
		info.setPhoneHome(custInfoArray[9]);
		info.setPhoneMobile(custInfoArray[10]);	
		info.setEmail(custInfoArray[11]);
		info.setTimeStamp(Instant.now().toString());		
		infoRepo.save(info);		
		logger.info(String.format("#### -> test-inbound Consumed message -> %s", message));		
	}

}
