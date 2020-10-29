package com.digitalhotelmanagement.digitalhotelmanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "address")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 1073658475702364716L;

	@GeneratedValue
	@Id
	private Integer id;

	@Column(nullable = false, length = 10)
	private String city;

	@Column(nullable = false, length = 10)
	private String country;

	@Column(nullable = false, length = 100)
	private String streetName;

	@Column(nullable = false, length = 7)
	private String postalCode;

	@Column(nullable = false, length = 20)
	private String type;

	@ManyToOne
	@JoinColumn(name = "sites_id") // site-> table name of site Entity _ field name(id)
	private SiteEntity siteDetails;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SiteEntity getSiteDetails() {
		return siteDetails;
	}

	public void setSiteDetails(SiteEntity siteDetails) {
		this.siteDetails = siteDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
