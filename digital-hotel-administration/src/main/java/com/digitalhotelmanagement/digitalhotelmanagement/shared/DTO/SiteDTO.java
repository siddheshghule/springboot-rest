package com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO;

import java.util.List;

public class SiteDTO {
	private Integer id;
	private String location;
	private String password;
	private List<AddressDTO> address;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddressDTO> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDTO> address) {
		this.address = address;
	}

	

}
