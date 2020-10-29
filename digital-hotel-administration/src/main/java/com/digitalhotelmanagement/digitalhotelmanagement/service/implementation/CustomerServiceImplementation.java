package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.CustomerEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.CustomerRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.CustomerService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.CustomerDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customer) throws Exception {

		CustomerEntity getCustomer = customerRepository.findByEmail(customer.getEmail());

		if (getCustomer != null)
			throw new Exception(ErrorMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());

		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customer, customerEntity);

		CustomerEntity createdUser = customerRepository.save(customerEntity);
		CustomerDTO returnValue = new CustomerDTO();
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}

	@Override
	public List<CustomerDTO> getCustomers(int page, int limit) throws Exception {

		List<CustomerDTO> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;

		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<CustomerEntity> customerPage = customerRepository.findAll(pageableRequest);

		List<CustomerEntity> customerEntities = customerPage.getContent();

		if (customerEntities.isEmpty())
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());

		for (CustomerEntity customerEntity : customerEntities) {

			CustomerDTO customerDTO = new CustomerDTO();
			BeanUtils.copyProperties(customerEntity, customerDTO);
			returnValue.add(customerDTO);
		}
		return returnValue;
	}

	// add email format verification and unique
	@Override
	public CustomerDTO getcustomerByEmail(String email) throws Exception {
		CustomerDTO returnValue = new CustomerDTO();
		CustomerEntity getByEmail = customerRepository.findByEmail(email);
		if (getByEmail == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(getByEmail, returnValue);
		return returnValue;
	}

	@Override
	public CustomerDTO getcustomerById(Integer id) throws Exception {
		CustomerDTO returnValue = new CustomerDTO();
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

		if (customerEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(customerEntity, returnValue);
		return returnValue;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer) throws Exception {
		CustomerDTO returnValue = new CustomerDTO();
		CustomerEntity customerEntity = customerRepository.findByEmail(customer.getEmail());

		if (customerEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		customerEntity.setTableno(customer.getTableno());
		customerEntity.setFirstname(customer.getFirstname());
		customerEntity.setLastname(customer.getLastname());
		customerEntity.setEmail(customer.getEmail());
		deleteCustomer(customer.getEmail());
		CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
		BeanUtils.copyProperties(updatedCustomer, returnValue);

		return returnValue;
	}

	@Override
	public void deleteCustomer(String email) throws Exception {
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		if (customerEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		customerRepository.delete(customerEntity);
	}

}
