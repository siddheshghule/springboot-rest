package com.digitalhotelmanagement.digitalhotelmanagement.validation;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	LOCATION_ALREADY_EXISTS("Location already exist, try with unique location name"),
	STREETNAME_ALREADY_EXISTS("Street name already exist, try with unique Street name"),
	TABLE_ALREADY_OCCUPIED("Table is already Occupied"),
	COULD_NOT_DELETE_USER_PROFILE("Could not delete user profile"), 
	NO_RECORD_FOUND("Record does not exist"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	EMAIL_ALREADY_EXISTS("Email already exists, try unique Email"),
	INTERNAL_SERVER_ERROR("Something went wrong. Please repeat this operation later."),
	PAGE_ERROR("PageLimit or page is less than 1. Read the documentation for a proper handling! Or the list is empty");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}