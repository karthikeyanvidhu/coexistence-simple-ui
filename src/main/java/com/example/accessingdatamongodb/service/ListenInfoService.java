package com.example.accessingdatamongodb.service;

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
	
	public List<ListenInfo> getAllCustomers(){
		List<ListenInfo> custList=new ArrayList<>();
		infoRepo.findAll().forEach(custList::add);
		return custList;		
	}
	
	public List<ListenInfo> findCustomer(String customerInfo){
		List<ListenInfo> custList=new ArrayList<>();
		infoRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile(customerInfo,customerInfo,customerInfo,customerInfo).forEach(custList::add);
		return custList;		
	}
	
	public ListenInfo findCustomerById(String customerInfo){		
		return infoRepo.findByCustomerId(customerInfo);
	}
	
	public void addCustomer(ListenInfo info) {
		infoRepo.save(info);
	}
}
