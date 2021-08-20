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
	
	public List<ListenInfo> getAllInfo(){
		List<ListenInfo> infoList=new ArrayList<>();
		infoRepo.findAll().forEach(infoList::add);
		return infoList;		
	}
	
	public void addInfo(ListenInfo info) {
		infoRepo.save(info);
	}
}
