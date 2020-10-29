package com.digitalhotelmanagement.digitalhotelmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request.AdminRequestModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.AdminResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.OperationalStatusModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.service.AdminService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AdminDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger("AdminController");
	@Autowired
	AdminService adminService;

	@ApiOperation(value = "Get list of Admins Web Service Endpoint", notes = "This Web Service Endpoint returns list of all admins with their details.")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<AdminResponseModel> getAdmin(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int pageLimit) throws Exception {
		logger.info("Get All Admin List. Pagination parameters: page: " + page + " pageLimit: " + pageLimit);

		if (page < 1 || page > pageLimit)
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());

		List<AdminResponseModel> returnValue = new ArrayList<>();
		List<AdminDTO> admins = adminService.getAdmin(page, pageLimit);
		for (AdminDTO adminDTO : admins) {
			AdminResponseModel response = new AdminResponseModel();
			Link linkSelf = linkTo(methodOn(AdminController.class).getAdminByUsername(adminDTO.getUsername()))
					.withSelfRel();
			BeanUtils.copyProperties(adminDTO, response);
			response.add(linkSelf);
			returnValue.add(response);
		}
		return returnValue;
	}

	@ApiOperation(value = "Get Admin Web Service Endpoint", notes = "This Web Service Endpoint finds Admin by using their unique Username and returns its details. For Example URL: /admin/username")
	@GetMapping(path = "/{username}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public AdminResponseModel getAdminByUsername(@PathVariable String username) throws Exception {
		logger.info("Get Admin by username: " + username);
		AdminResponseModel returnValue = new AdminResponseModel();
		AdminDTO getAdmin = adminService.getByUsername(username);
		BeanUtils.copyProperties(getAdmin, returnValue);

		Link linkRoot = linkTo(methodOn(AdminController.class).getAdmin(1, 10)).withRel("root");
		Link linkSelf = linkTo(methodOn(AdminController.class).getAdminByUsername(username)).withSelfRel();
		returnValue.add(linkSelf);
		returnValue.add(linkRoot);
		return returnValue;
	}

	@ApiOperation(value = "Create Admin Web Service Endpoint", notes = "This Web Service Endpoint creates and returns Admin.")
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AdminResponseModel createAdmin(@RequestBody AdminRequestModel request) throws Exception {
		logger.info("Create Admin");

		if (request.getFirstname().isEmpty() || request.getLastname().isEmpty() || request.getEmail().isEmpty()
				|| request.getUsername().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		AdminResponseModel returnValue = new AdminResponseModel();
		AdminDTO admindto = new AdminDTO();
		BeanUtils.copyProperties(request, admindto);

		AdminDTO createdAdmin = adminService.createAdmin(admindto);
		BeanUtils.copyProperties(createdAdmin, returnValue);

		return returnValue;

	}

	@ApiOperation(value = "Update existing Admin Web Service Endpoint", notes = "This Web Service Endpoint updates and returns updated Admin. Requirement: admin with existing Username")
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AdminResponseModel updateAdmin(@RequestBody AdminRequestModel request) throws Exception {
		logger.info("Update Admin with username:" + request.getUsername());
		AdminResponseModel returnValue = new AdminResponseModel();
		AdminDTO adminDTO = new AdminDTO();
		BeanUtils.copyProperties(request, adminDTO);
		AdminDTO updateAdmin = adminService.updateAdmin(adminDTO);
		BeanUtils.copyProperties(updateAdmin, returnValue);
		return returnValue;
	}

	@ApiOperation(value = "Delete Admin Web Service Endpoint", notes = "This Web Service Endpoint deletes Customer using their Username. For Example URL: /admin/username")
	@DeleteMapping(path = "/{username}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public OperationalStatusModel deleteAdmin(@PathVariable String username) throws Exception {
		logger.info("Delete admin with username: " + username);
		OperationalStatusModel returnValue = new OperationalStatusModel();
		returnValue.setOperationStatus(StatusResponseModel.FAILURE.name());
		adminService.deleteAdmin(username);
		returnValue.setOperationName(StatusResponseModel.DELETE.name());
		returnValue.setOperationStatus(StatusResponseModel.SUCCESS.name());
		return returnValue;
	}

}
