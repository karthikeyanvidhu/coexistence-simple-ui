package com.example.accessingdatamongodb.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.accessingdatamongodb.engine.Producer;
import com.example.accessingdatamongodb.repository.CustomerInfo;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@Service
public class ListenInfoService {
	
	private final Logger logger = LoggerFactory.getLogger(ListenInfoService.class);

	@Autowired
	private ListenInfoRepository infoRepo;
	
	private final Producer producer;
	
	@Value("${spring.kafka.res.add.cust}")
	private String GENAPP_RES_ADD_CUST;	
	
	@Value("${spring.kafka.res.upd.cust}")
	private String GENAPP_RES_UPD_CUST;		

	@Autowired
	ListenInfoService(Producer producer) {
		this.producer = producer;
	}

	public List<CustomerInfo> getAllCustomers() {
		List<CustomerInfo> custList = new ArrayList<>();
		infoRepo.findAll().forEach(custList::add);
		return custList;
	}

	public List<CustomerInfo> findCustomer(String customerInfo) {
		List<CustomerInfo> custList = new ArrayList<>();
		infoRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile(customerInfo, customerInfo,
				customerInfo, customerInfo).forEach(custList::add);
		return custList;
	}

	public CustomerInfo findCustomerById(String customerInfo) {
		return infoRepo.findByCustomerId(customerInfo);
	}

	public void addCustomer(CustomerInfo info) {
		infoRepo.save(info);
	}

	//Add customer from UI Frontend (FS Cloud)
	public void addCustomer(String customerInfo) {
		System.out.println(customerInfo);
		logger.info(String.format("#### ->Add customer from UI Frontend (FS Cloud) -> %s", customerInfo));		
		String[] custInfoArray = customerInfo.split(";");
		CustomerInfo info = new CustomerInfo();
		String generatedCorrelationId=generateRandomDigits(10);
		info.setCorrelationId(generatedCorrelationId);
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
		String queueString=generatedCorrelationId+customerInfo;
		this.producer.sendMessage(queueString, GENAPP_RES_ADD_CUST);
		System.out.println(queueString);
	}
	
	//Update customer from UI Frontend (FS Cloud)
	public void updateCustomer(String customerInfo) {
		System.out.println(customerInfo);
		logger.info(String.format("#### ->Update customer from UI Frontend (FS Cloud) -> %s", customerInfo));		
		String[] custInfoArray = customerInfo.split(";");
		CustomerInfo info = new CustomerInfo();
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
		this.producer.sendMessage(customerInfo, GENAPP_RES_UPD_CUST);
		System.out.println(customerInfo);
	}


	
	public String generateRandomDigits(Integer n) {
	    Integer m = (int) Math.pow(10, n - 1);
	    String randomNumber= ""+ (m + new Random().nextInt(9 * m));
	    return randomNumber;
	}
	
	public void putQueue(String msg,String queue) {
	this.producer.sendMessage(msg, queue);
	}
}
