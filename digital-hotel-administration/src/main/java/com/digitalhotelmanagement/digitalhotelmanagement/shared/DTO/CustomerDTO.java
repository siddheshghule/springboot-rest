package com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = -7594416379427489477L;

	private Integer id;
	private int tableno;
	private String firstname;
	private String lastname;
	private String email;

	public int getTableno() {
		return tableno;
	}

	public void setTableno(int tableno) {
		this.tableno = tableno;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
