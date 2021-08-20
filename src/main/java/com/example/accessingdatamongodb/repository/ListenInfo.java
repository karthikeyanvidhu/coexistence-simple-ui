package com.example.accessingdatamongodb.repository;



import org.springframework.data.annotation.Id;

public class ListenInfo {
	
	
	public ListenInfo() {}
	
	public ListenInfo(String timeStamp, String info) {
		this.timeStamp = timeStamp;
		this.info = info;
	}
	
	@Id
	private String timeStamp;
	private String info;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ListenInfo [timeStamp=" + timeStamp + ", info=" + info + "]";
	}
	

}
