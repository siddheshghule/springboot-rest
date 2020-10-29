package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

public class AdminResponseModel extends RepresentationModel<AdminResponseModel>{
	
	private Integer adminid;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	
	
	
}
