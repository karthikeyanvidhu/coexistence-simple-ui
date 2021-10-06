package com.example.accessingdatamongodb.engine;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.accessingdatamongodb.repository.CustomerInfo;
import com.example.accessingdatamongodb.repository.LatencyTime;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private ListenInfoRepository infoRepo;	
	
	private final Producer producer;

	@Autowired
	Consumer(Producer producer) {
		this.producer = producer;
	}
	
	@Value("${spring.kafka.res.ack.cust}")
	private String GENAPP_RES_ACK_CUST;	
	
	@Value("${spring.kafka.req.add.cust}")
	private String GENAPP_REQ_ADD_CUST;
	
	//Add customer from Mainframe
	@KafkaListener(topics = "${spring.kafka.req.add.cust}", groupId = "group_id")
	public void add(String message) throws IOException {
		logger.info(String.format("#### ->ADD customer from Mainframe -> %s", message));		
		String[] custInfoArray=message.split(";");
		CustomerInfo info=new CustomerInfo();
		info.setCorrelationId(custInfoArray[0]);
		info.setOperation(custInfoArray[1]);
		info.setCustomerId(custInfoArray[2]);
		info.setFirstName(custInfoArray[3]);
		info.setLastName(custInfoArray[4]);
		info.setDateOfBirth(custInfoArray[5]);
		info.setHouseNumber(custInfoArray[6]);
		info.setHouseName(custInfoArray[7]);
		info.setPostCode(custInfoArray[8]);
		info.setPhoneHome(custInfoArray[9]);
		info.setPhoneMobile(custInfoArray[10]);	
		info.setEmail(custInfoArray[11]);
		info.setTimeStamp(Instant.now().toString());		
		infoRepo.save(info);	
		String ackString=custInfoArray[0]+";"+"ACKA"+";"+custInfoArray[2];
		this.producer.sendMessage(ackString, "GENAPP.RES.ACK.CUST");
		System.out.println("Acknowledgement String:"+ ackString);	
		System.out.println("GENAPP.REQ.ADD.CUST - inbound Consumed message:"+ message);		
	}
	
	//Update customer from Mainframe
	@KafkaListener(topics = "${spring.kafka.req.upd.cust}", groupId = "group_id")
	public void update(String message) throws IOException {
		logger.info(String.format("#### ->Update customer from Mainframe -> %s", message));		
		String[] custInfoArray=message.split(";");		
		CustomerInfo info=new CustomerInfo();
		info.setCorrelationId(custInfoArray[0]);
		info.setOperation(custInfoArray[1]);
		info.setCustomerId(custInfoArray[2]);
		info.setFirstName(custInfoArray[3]);
		info.setLastName(custInfoArray[4]);
		info.setDateOfBirth(custInfoArray[5]);
		info.setHouseNumber(custInfoArray[6]);
		info.setHouseName(custInfoArray[7]);
		info.setPostCode(custInfoArray[8]);
		info.setPhoneHome(custInfoArray[9]);
		info.setPhoneMobile(custInfoArray[10]);
		info.setEmail(custInfoArray[11]);
		info.setTimeStamp(Instant.now().toString());		
		infoRepo.save(info);
		String ackString=custInfoArray[0]+";"+"ACKU"+";"+custInfoArray[2];
		this.producer.sendMessage(ackString, GENAPP_RES_ACK_CUST);
		System.out.println("Acknowledgement String:"+ ackString);	
		System.out.println("GENAPP.REQ.UPD.CUST - inbound Consumed message:"+ message);		
	}	
	
	
	//Update customer from Mainframe
	@KafkaListener(topics = "${spring.kafka.req.ack.cust}", groupId = "group_id")
	public void updateBasedOnAcknowledgement(String message) throws IOException {
	    System.out.println("Update Customer based on Acknowledgement");
		String[] custInfoArray=message.split(";");		
		String correlationId=custInfoArray[0];
		String customerId=custInfoArray[2];
		CustomerInfo info=infoRepo.findByCorrelationId(correlationId);
		infoRepo.delete(info);
		info.setCustomerId(customerId);
		infoRepo.save(info);

		LatencyTime.to =Instant.now();
		Long latency= LatencyTime.to.toEpochMilli()  - LatencyTime.from.toEpochMilli() ;
	    System.out.println("Updated Customer: "+info );		
	    System.out.println("Total Latency from FSCloud ->Mainframes-> FSCloud UI in Milliseconds"+latency + "or "+ latency/1000 +" Seconds" );
	}
	
	

}
