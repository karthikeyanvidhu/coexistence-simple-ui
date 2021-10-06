package com.example.accessingdatamongodb.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamongodb.repository.CustomerInfo;
import com.example.accessingdatamongodb.repository.LatencyTime;
import com.example.accessingdatamongodb.service.ListenInfoService;

@RestController
@CrossOrigin
public class UiController {

	@Autowired
	private ListenInfoService infoService;
	
	@RequestMapping(value = "/customers")
	public List<CustomerInfo> getAllCustomers() {
		return infoService.getAllCustomers();
	}
	
	@RequestMapping(value = "/customers/{customerInfo}")
	public List<CustomerInfo> getCustomer(@PathVariable String customerInfo) {
		return infoService.findCustomer(customerInfo);
	}
	
	@RequestMapping(value = "/customers/id/{customerId}",method = RequestMethod.GET)
	public List<CustomerInfo> getCustomerById(@PathVariable String customerId) {
		return infoService.findCustomer(customerId);
	}
	
	@PostMapping(value = "/customers/update")
	public String updateCustomerId(@RequestParam("message") String message) {
		System.out.println("Inside /customers/update");
		infoService.updateCustomer(message);
		return "/customers/update successful.customer details updated";
	}
	
	@PostMapping(value = "/customers/add")
	public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
		System.out.println("Inside /customers/add " );
		LatencyTime.from =Instant.now();
		infoService.addCustomer(message);
		return "/customers/add successful.customer details added";
	}
	
	@RequestMapping(value = "/queue")
	public String putQueue(@RequestParam  String msg,@RequestParam String queue ) {
		System.out.println("Inside /queue " + queue + msg);
		infoService.putQueue(msg, queue);
		return "Msg added in Queue";
	}

}
