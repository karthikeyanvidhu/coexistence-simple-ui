package com.example.accessingdatamongodb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface ListenInfoRepository extends CrudRepository<ListenInfo, Integer> {
	ListenInfo findByCustomerId(String customerId);
	List<ListenInfo> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile(String firstName,String lastName,String custId,String phone);
}
