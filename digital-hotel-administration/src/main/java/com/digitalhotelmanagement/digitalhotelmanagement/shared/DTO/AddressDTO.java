package com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO;

public class AddressDTO {
	private Integer id;
	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type;
	private SiteDTO siteDetails;

	public AddressDTO() {
	}

	public AddressDTO(Integer id, String city, String country, String streetName, String postalCode, String type) {
		this.id = id;
		this.city = city;
		this.country = country;
		this.streetName = streetName;
		this.postalCode = postalCode;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public SiteDTO getSiteDetails() {
		return siteDetails;
	}

	public void setSiteDetails(SiteDTO siteDetails) {
		this.siteDetails = siteDetails;
	}

}
