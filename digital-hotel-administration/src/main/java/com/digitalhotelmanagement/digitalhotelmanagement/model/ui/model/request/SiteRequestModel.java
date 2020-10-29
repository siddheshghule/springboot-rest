package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request;

import java.util.List;

public class SiteRequestModel {
	private String password;
	private String location;
	private List<AddressRequestModel> address;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<AddressRequestModel> getAddress() {
		return address;
	}
	public void setAddress(List<AddressRequestModel> address) {
		this.address = address;
	}



}
