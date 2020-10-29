package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;

public interface AddressesService {

	List<AddressDTO> getAddresses(String location) throws Exception;

	AddressDTO getAddressByStreetName(String streetName, String location) throws Exception;
}
