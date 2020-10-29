package com.digitalhotelmanagement.digitalhotelmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "admin")
public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer adminid;

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@Column(nullable = false, length = 30)
	private String firstname;

	@Column(nullable = false, length = 30)
	private String lastname;

	@Column(nullable = false, length = 30)
	private String email; // look for validating email

	@Column
	private String encryptedPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}
