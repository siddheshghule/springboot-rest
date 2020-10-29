package com.digitalhotelmanagement.digitalhotelmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request.CustomerRequestModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.CustomerResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.OperationalStatusModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.service.CustomerService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.CustomerDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = Logger.getLogger("ClientController");
	@Autowired
	CustomerService customerServiceImplementation;

	/*
	 * getCustomer getCustomerByEmail createCustomer updateCustomer deleteCustomer
	 */
	@ApiOperation(value = "Get list of Customers Web Service Endpoint", notes = "This Web Service Endpoint returns list of all customers with their details.")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<CustomerResponseModel> getCustomer(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) throws Exception {
		logger.info("Get All Admin List. Pagination parameters: page: " + page + " pageLimit: " + limit);
		List<CustomerResponseModel> returnValue = new ArrayList<>();

		if (page < 0 || page > limit)
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());
		List<CustomerDTO> customers = customerServiceImplementation.getCustomers(page, limit);

		for (CustomerDTO customerDTO : customers) {
			CustomerResponseModel response = new CustomerResponseModel();
			Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerByEmail(customerDTO.getEmail()))
					.withSelfRel();
			BeanUtils.copyProperties(customerDTO, response);
			response.add(selfLink);
			returnValue.add(response);
		}

		return returnValue;
	}

	@ApiOperation(value = "Get unique Customer Web Service Endpoint", notes = "This Web Service Endpoint returns Customer by using their Email Address with its details. For Example URL: /customer/abcd@gmail.com")
	@GetMapping(path = "/{email}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerResponseModel getCustomerByEmail(@PathVariable String email) throws Exception {
		logger.info("Get Client by email: " + email);
		CustomerResponseModel returnValue = new CustomerResponseModel();
		if (customerServiceImplementation.getcustomerByEmail(email) == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(customerServiceImplementation.getcustomerByEmail(email), returnValue);
		Link rootLink = linkTo(methodOn(CustomerController.class).getCustomer(1, 10)).withRel("Root");
		Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerByEmail(email)).withSelfRel();
		returnValue.add(selfLink);
		returnValue.add(rootLink);
		return returnValue;
	}
	@ApiOperation(value = "Create Customer Web Service Endpoint", notes = "This Web Service Endpoint creates and returns Customer.")
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CustomerResponseModel createCustomer(@RequestBody CustomerRequestModel requestModel) throws Exception {
		logger.info("Create Client");
		if (requestModel.getFirstname().isEmpty() || requestModel.getEmail().isEmpty()
				|| requestModel.getLastname().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		if (requestModel.getTableno() == 0)
			throw new Exception("Table number cannot be '0' or empty");

		CustomerResponseModel returnValue = new CustomerResponseModel();
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(requestModel, customerDTO);

		CustomerDTO createdCustomer = customerServiceImplementation.createCustomer(customerDTO);
		BeanUtils.copyProperties(createdCustomer, returnValue);

		return returnValue;

	}
	@ApiOperation(value = "Update existing Customer Web Service Endpoint", notes = "This Web Service Endpoint updates and returns updated Customer. Requirement: customer with existing Email Address")
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerResponseModel updateCustomer(@RequestBody CustomerRequestModel requestModel) throws Exception {
		logger.info("Update Client");
		if (requestModel.getFirstname().isEmpty() || requestModel.getEmail().isEmpty()
				|| requestModel.getLastname().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		CustomerResponseModel returnValue = new CustomerResponseModel();

		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(requestModel, customerDTO);

		CustomerDTO updatedUser = customerServiceImplementation.updateCustomer(customerDTO);
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;

	}
	@ApiOperation(value = "Delete Customer Web Service Endpoint", notes = "This Web Service Endpoint deletes Customer using their Email Address. For Example URL: /customer/abcd@gmail.com")
	@DeleteMapping(path = "/{email}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationalStatusModel deleteCustomer(@PathVariable String email) throws Exception {
		logger.info("Delete Client:" + email);
		OperationalStatusModel returnValue = new OperationalStatusModel();

		customerServiceImplementation.deleteCustomer(email);
		returnValue.setOperationName(StatusResponseModel.DELETE.name());

		returnValue.setOperationStatus(StatusResponseModel.SUCCESS.name());

		return returnValue;
	}

}
