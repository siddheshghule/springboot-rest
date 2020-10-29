package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.CustomerDTO;

public interface CustomerService {
	CustomerDTO createCustomer(CustomerDTO customer) throws Exception;

	List<CustomerDTO> getCustomers(int page, int limit) throws Exception;

	CustomerDTO getcustomerByEmail(String email) throws Exception;

	CustomerDTO getcustomerById(Integer id) throws Exception;

	void deleteCustomer(String email) throws Exception;

	CustomerDTO updateCustomer(CustomerDTO customer) throws Exception;
}
