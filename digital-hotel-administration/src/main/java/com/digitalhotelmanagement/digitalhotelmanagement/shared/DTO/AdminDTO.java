package com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO;

import java.io.Serializable;

public class AdminDTO implements Serializable {

	private static final long serialVersionUID = 669663016656111836L;
	
	private Integer adminid;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	
	
	
	
	
	
	

}
