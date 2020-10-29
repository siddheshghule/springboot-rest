package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request;

public class AddressRequestModel {
//	private String addressId; // this should be temporary make changes to generate automated value
	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type; // Hostel, hotel, restaurant;
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

	

}
