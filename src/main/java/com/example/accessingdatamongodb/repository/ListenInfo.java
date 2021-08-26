package com.example.accessingdatamongodb.repository;



import org.springframework.data.annotation.Id;

public class ListenInfo {
	
	public ListenInfo() {}	
	
	public ListenInfo(String correlationId, String operation, String customerId, String firstName, String lastName,
			String dateOfBirth, String houseName, String houseNumber, String postCode, String phoneHome,
			String phoneMobile, String email, String timeStamp) {
		super();
		this.correlationId = correlationId;
		this.operation = operation;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.houseName = houseName;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.email = email;
		this.timeStamp = timeStamp;
	}


	private String correlationId;
	private String operation;
	@Id
	private String customerId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String houseName;
	private String houseNumber;
	private String postCode;
	private String phoneHome;
	private String phoneMobile;
	private String email;
	private String timeStamp;

	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhoneHome() {
		return phoneHome;
	}
	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}
	public String getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "ListenInfo [correlationId=" + correlationId + ", operation=" + operation + ", customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", houseName=" + houseName + ", houseNumber=" + houseNumber + ", postCode=" + postCode
				+ ", phoneHome=" + phoneHome + ", phoneMobile=" + phoneMobile + ", email=" + email + ", timeStamp="
				+ timeStamp + "]";
	}


}
