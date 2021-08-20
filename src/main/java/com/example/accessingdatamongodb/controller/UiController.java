package com.example.accessingdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamongodb.repository.ListenInfo;
import com.example.accessingdatamongodb.service.ListenInfoService;

@RestController
@CrossOrigin
public class UiController {

	@Autowired
	private ListenInfoService infoService;
	
	@RequestMapping(value = "/messages")
	public List<ListenInfo> getAllMessages() {
		return infoService.getAllInfo();
	}

}
