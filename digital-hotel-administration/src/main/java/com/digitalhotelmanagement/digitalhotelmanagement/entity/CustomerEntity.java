package com.digitalhotelmanagement.digitalhotelmanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "customer")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = -2720667728836029247L;

	@GeneratedValue
	@Id
	private Integer id;

	@Column(nullable = false)
	private int tableno;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false, length = 70)
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
