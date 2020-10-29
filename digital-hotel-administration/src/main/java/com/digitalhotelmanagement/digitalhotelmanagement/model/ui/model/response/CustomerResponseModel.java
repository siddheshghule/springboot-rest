package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

public class CustomerResponseModel extends RepresentationModel<CustomerResponseModel> {
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

	@Override
	public String toString() {
		return String.format("CustomerResponseModel [customerid=%s, tableno=%s, firstname=%s, lastname=%s, email=%s]",
				id, tableno, firstname, lastname, email);
	}

}
