package com.example.accessingdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamongodb.repository.ListenInfo;
import com.example.accessingdatamongodb.service.ListenInfoService;

@RestController
@CrossOrigin
public class UiController {

	@Autowired
	private ListenInfoService infoService;
	
	@RequestMapping(value = "/customers")
	public List<ListenInfo> getAllCustomers() {
		return infoService.getAllCustomers();
	}
	
	@RequestMapping(value = "/customers/{customerInfo}")
	public List<ListenInfo> getCustomer(@PathVariable String customerInfo) {
		return infoService.findCustomer(customerInfo);
	}
	
	@RequestMapping(value = "/customers/id/{customerId}",method = RequestMethod.GET)
	public List<ListenInfo> getCustomerById(@PathVariable String customerId) {
		return infoService.findCustomer(customerId);
	}
	
	@PostMapping(value = "/customers/update")
	public String updateCustomerId(@RequestParam("message") String message) {
		System.out.println("Inside Update");
		infoService.updateCustomer(message);
		return "customer details updated";
	}

}
