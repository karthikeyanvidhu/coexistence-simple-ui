package com.example.accessingdatamongodb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface ListenInfoRepository extends CrudRepository<CustomerInfo, Integer> {
	CustomerInfo findByCustomerId(String customerId);
	CustomerInfo findByCorrelationId(String correlationId);
	List<CustomerInfo> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrCustomerIdOrPhoneMobile(String firstName,String lastName,String custId,String phone);
}
