package com.digitalhotelmanagement.digitalhotelmanagement.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "site")
public class SiteEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8041707764827346754L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String location;

	@Column
	private String encryptedPassword;

	@OneToMany(mappedBy = "siteDetails", cascade = CascadeType.ALL)
	private List<AddressEntity> address;

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}

	public SiteEntity() {
	}

	public SiteEntity(Integer id, String location, String encryptedPassword, List<AddressEntity> address) {
		this.id = id;
		this.location = location;
		this.encryptedPassword = encryptedPassword;
		this.address = address;
	}

	public SiteEntity(Integer id, String location, String encryptedPassword) {
		this.id = id;
		this.location = location;
		this.encryptedPassword = encryptedPassword;
	}
}