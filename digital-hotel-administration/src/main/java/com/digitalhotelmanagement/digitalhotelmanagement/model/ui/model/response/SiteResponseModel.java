package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class SiteResponseModel extends RepresentationModel<SiteResponseModel>{

	private Integer id;
	private String location;
	private List<AddressResponseModel> address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<AddressResponseModel> getAddress() {
		return address;
	}
	public void setAddress(List<AddressResponseModel> address) {
		this.address = address;
	}
	

	
}
