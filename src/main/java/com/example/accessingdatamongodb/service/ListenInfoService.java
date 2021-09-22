package com.example.accessingdatamongodb.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessingdatamongodb.repository.ListenInfo;
import com.example.accessingdatamongodb.repository.ListenInfoRepository;

@Service
public class ListenInfoService {

	@Autowired
	private ListenInfoRepository infoRepo;

	public List<ListenInfo> getAllCustomers() {
		List<ListenInfo> custList = new ArrayList<>();
		infoRepo.findAll().forEach(custList::add);
		return custList;
	}

	public List<ListenInfo> findCustomer(String customerInfo) {
		List<ListenInfo> custList = new ArrayList<>();
		infoRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile(customerInfo, customerInfo,
				customerInfo, customerInfo).forEach(custList::add);
		return custList;
	}

	public ListenInfo findCustomerById(String customerInfo) {
		return infoRepo.findByCustomerId(customerInfo);
	}

	public void addCustomer(ListenInfo info) {
		infoRepo.save(info);
	}

	public void updateCustomer(String customerInfo) {
		System.out.println(customerInfo);
		String[] custInfoArray = customerInfo.split(";");
		ListenInfo info = new ListenInfo();
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
	}
}
