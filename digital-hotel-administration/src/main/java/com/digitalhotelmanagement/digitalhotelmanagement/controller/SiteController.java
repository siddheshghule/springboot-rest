package com.digitalhotelmanagement.digitalhotelmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request.SiteRequestModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.AddressResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.OperationalStatusModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.SiteResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.service.AddressesService;
import com.digitalhotelmanagement.digitalhotelmanagement.service.SiteService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.SiteDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	SiteService siteService;

	@Autowired
	AddressesService addressesService;

	@ApiOperation(value = "Get list of Sites Web Service Endpoint", notes = "This Web Service Endpoint returns list of all Sites with their details including Addresses and their details, if existing.")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<SiteResponseModel> getSite(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		List<SiteResponseModel> returnValue = new ArrayList<>();

		List<SiteDTO> sites = siteService.getSiteDetails(page, limit);
		for (SiteDTO siteDTO : sites) {
			SiteResponseModel getSite = modelMapper.map(siteDTO, SiteResponseModel.class);
//			BeanUtils.copyProperties(siteDTO, getSite);  BeanUtils is not suitable in mapping of entities with relation mapping

			// Adding link to each address
			List<AddressResponseModel> addresses = getSite.getAddress();
			List<AddressResponseModel> addresseswithLink = new ArrayList<>();
			for (AddressResponseModel address : addresses) {
				Link addressLink = linkTo(methodOn(SiteController.class)
						.getSiteAddressByStreetName(siteDTO.getLocation(), address.getStreetName()))
								.withRel("Individual Street");
				address.add(addressLink);
				addresseswithLink.add(address);

			}
			getSite.setAddress(addresseswithLink);
			Link selfLink = linkTo(methodOn(SiteController.class).getSite(1, 10)).withSelfRel();
			getSite.add(selfLink);
			returnValue.add(getSite);
		}

		return returnValue;
	}

	@ApiOperation(value = "Create Site Web Service Endpoint", notes = "This Web Service Endpoint creates and returns Site with or without its one or many Addresses. Preferably add addresses.")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public SiteResponseModel createSite(@RequestBody SiteRequestModel siteDetails) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		SiteDTO siteDTO = modelMapper.map(siteDetails, SiteDTO.class);

		SiteDTO createdSite = siteService.createSite(siteDTO);
		SiteResponseModel returnValue = modelMapper.map(createdSite, SiteResponseModel.class);

		return returnValue;
	}

	@ApiOperation(value = "Delete Site Web Service Endpoint", notes = "This Web Service Endpoint deletes Site  using location name and returns its details. For Example URL: /site/locationName")
	@DeleteMapping(path = "/{location}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OperationalStatusModel deleteSite(@PathVariable String location) throws Exception {
		OperationalStatusModel returnValue = new OperationalStatusModel();
		siteService.deleteSite(location);
		returnValue.setOperationName(StatusResponseModel.DELETE.name());
		returnValue.setOperationStatus(StatusResponseModel.SUCCESS.name());
		return returnValue;
	}

	@ApiOperation(value = "Update Site Web Service Endpoint", notes = "This Web Service Endpoint updates Site details using location name and returns its details.")
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public SiteResponseModel updateSite(@RequestBody SiteRequestModel request) throws Exception {
		SiteResponseModel returnValue = new SiteResponseModel();

		ModelMapper modelMapper = new ModelMapper();
		SiteDTO siteDTO = modelMapper.map(request, SiteDTO.class);

		SiteDTO updatedSite = siteService.updateSite(siteDTO);
		returnValue = modelMapper.map(updatedSite, SiteResponseModel.class);
		return returnValue;
	}

	@ApiOperation(value = "Get Site Addresses Web Service Endpoint", notes = "This Web Service Endpoint finds List of site addresses using its Location Name returns its details. For Example URL: /site/locationName")
	@GetMapping(path = "/{location}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_RSS_XML_VALUE })
	public List<AddressResponseModel> getSiteAddresses(@PathVariable String location) throws Exception {
		List<AddressResponseModel> returnValue = new ArrayList<>();
		List<AddressResponseModel> returnValuewithLink = new ArrayList<>();
		List<AddressDTO> getAddress = addressesService.getAddresses(location);

		ModelMapper modelMapper = new ModelMapper();
		if (!getAddress.isEmpty()) {
			Type listType = new TypeToken<List<AddressResponseModel>>() {
			}.getType();
			returnValue = modelMapper.map(getAddress, listType);
		}
		for (AddressResponseModel value : returnValue) {
			Link selfLink = linkTo(
					methodOn(SiteController.class).getSiteAddressByStreetName(location, value.getStreetName()))
							.withRel("Individual Street");
			value.add(selfLink);
			returnValuewithLink.add(value);
		}
		return returnValuewithLink;
	}

	@ApiOperation(value = "Get Site Address Web Service Endpoint", notes = "This Web Service Endpoint finds single Address of a Site using its Location name and Street name returns its details. For Example URL: /site/locationName/streetName")
	@GetMapping(path = "/{location}/{streetName}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_RSS_XML_VALUE })
	public AddressResponseModel getSiteAddressByStreetName(@PathVariable String location,
			@PathVariable String streetName) throws Exception {
		AddressResponseModel returnValue = new AddressResponseModel();
		AddressDTO getAddress = addressesService.getAddressByStreetName(streetName, location);
		ModelMapper modelMapper = new ModelMapper();
		if (getAddress != null) {
			returnValue = modelMapper.map(getAddress, AddressResponseModel.class);
		}
		Link selfLink = linkTo(methodOn(SiteController.class).getSiteAddressByStreetName(location, streetName))
				.withSelfRel();
		Link rootLink = linkTo(methodOn(SiteController.class).getSiteAddresses(location)).withRel("root");
		returnValue.add(selfLink);
		returnValue.add(rootLink);
		return returnValue;
	}

}
