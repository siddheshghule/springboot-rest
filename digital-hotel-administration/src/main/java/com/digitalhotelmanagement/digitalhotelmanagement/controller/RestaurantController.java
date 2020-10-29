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

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request.BeveragesRequestModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.request.FoodRequestModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.BeveragesResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.FoodResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.OperationalStatusModel;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.service.BeveragesService;
import com.digitalhotelmanagement.digitalhotelmanagement.service.FoodService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.BeveragesDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.FoodDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

	private static final Logger logger = Logger.getLogger("RestaurantController");

	@Autowired
	FoodService foodService;

	@Autowired
	BeveragesService beveragesService;

	/*
	 * Food
	 */

	@ApiOperation(value = "Get list of Food items Web Service Endpoint", notes = "This Web Service Endpoint returns list of all Food items with their details.")
	@GetMapping(path = "/food", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<FoodResponseModel> getAllFoodItems(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int pageLimit) throws Exception {
		logger.info("Get All Food Item List. Pagination parameters: page: " + page + " pageLimit: " + pageLimit);

		if (page < 1 || page > pageLimit)
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());

		List<FoodResponseModel> returnValue = new ArrayList<>();
		List<FoodDTO> getFoodItems = foodService.getItems(page, pageLimit);
		for (FoodDTO fooditem : getFoodItems) {
			FoodResponseModel response = new FoodResponseModel();
			Link selfLink = linkTo(methodOn(RestaurantController.class).getFoodItemsByName(fooditem.getItem()))
					.withSelfRel();
			BeanUtils.copyProperties(fooditem, response);
			response.add(selfLink);
			returnValue.add(response);
		}
		return returnValue;
	}

	@ApiOperation(value = "Get Food item Web Service Endpoint", notes = "This Web Service Endpoint finds Food item using its unique Item Name returns its details. For Example URL: /food/itemName")
	@GetMapping(path = "/food/{item}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public FoodResponseModel getFoodItemsByName(@PathVariable String item) throws Exception {
		logger.info("Get Food Item by item name. Parameters: itemName: " + item);

		FoodResponseModel returnValue = new FoodResponseModel();
		FoodDTO getFoodItems = foodService.getSingleItem(item);
		BeanUtils.copyProperties(getFoodItems, returnValue);
		Link selfLink = linkTo(methodOn(RestaurantController.class).getFoodItemsByName(item)).withSelfRel();
		Link rootLink = linkTo(methodOn(RestaurantController.class).getAllFoodItems(1, 10)).withRel("Root");
		returnValue.add(selfLink);
		returnValue.add(rootLink);
		return returnValue;
	}

	@ApiOperation(value = "Create Food item Web Service Endpoint", notes = "This Web Service Endpoint creates and returns Food item.")
	@PostMapping(path = "/food", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public FoodResponseModel createFoodItem(@RequestBody FoodRequestModel request) throws Exception {
		FoodResponseModel returnValue = new FoodResponseModel();
		FoodDTO requestDTO = new FoodDTO();
		BeanUtils.copyProperties(request, requestDTO);
		FoodDTO getFoodItem = foodService.setFoodItem(requestDTO);
		BeanUtils.copyProperties(getFoodItem, returnValue);
		return returnValue;
	}

	@ApiOperation(value = "Update Food item Web Service Endpoint", notes = "This Web Service Endpoint updates food item using their item name and returns its details.")
	@PutMapping(path = "/food", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public FoodResponseModel updateFoodItem(@RequestBody FoodRequestModel request) throws Exception {
		FoodResponseModel returnValue = new FoodResponseModel();
		FoodDTO requestDTO = new FoodDTO();
		BeanUtils.copyProperties(request, requestDTO);

		FoodDTO getFoodItem = foodService.updateFoodItem(requestDTO);
		BeanUtils.copyProperties(getFoodItem, returnValue);
		return returnValue;
	}

	@ApiOperation(value = "Delete Beverage item Web Service Endpoint", notes = "This Web Service Endpoint deletes beverage item using their item name and returns its details. For Example URL: food/itemName")
	@DeleteMapping(path = "/food/{item}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public OperationalStatusModel deleteFoodItem(@PathVariable String item) throws Exception {
		OperationalStatusModel returnValue = new OperationalStatusModel();
		returnValue.setOperationName(StatusResponseModel.DELETE.name());
		returnValue.setOperationStatus(StatusResponseModel.FAILURE.name());
		StatusResponseModel getStatus = foodService.deleteItem(item);
		if (getStatus == StatusResponseModel.SUCCESS) {
			returnValue.setOperationStatus(StatusResponseModel.SUCCESS.name());
		}
		return returnValue;
	}

	/*
	 * Beverages
	 */

	@ApiOperation(value = "Get list of Beverage items Web Service Endpoint", notes = "This Web Service Endpoint returns list of all Beverage items with their details.")
	@GetMapping(path = "/beverages", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<BeveragesResponseModel> getAllBeveragesItems(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int pageLimit) throws Exception {
		logger.info("Get All Beverage Item List. Pagination parameters: page: " + page + " pageLimit: " + pageLimit);

		if (page < 1 || page > pageLimit)
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());

		List<BeveragesResponseModel> returnValue = new ArrayList<>();
		List<BeveragesDTO> getBeveragesItems = beveragesService.getItems(page, pageLimit);
		for (BeveragesDTO beverage : getBeveragesItems) {
			BeveragesResponseModel response = new BeveragesResponseModel();
			BeanUtils.copyProperties(beverage, response);
			Link selfLink = linkTo(methodOn(RestaurantController.class).getBeveragesItemByItemName(beverage.getItem()))
					.withSelfRel();
			response.add(selfLink);
			returnValue.add(response);
		}
		return returnValue;
	}

	@ApiOperation(value = "Get Beverage item Web Service Endpoint", notes = "This Web Service Endpoint finds Beverage item using its unique Item Name returns its details. For Example URL: /beverages/itemName")
	@GetMapping(path = "/beverages/{item}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public BeveragesResponseModel getBeveragesItemByItemName(@PathVariable String item) throws Exception {
		logger.info("Get Beverage Item by Item name. Parameters: itemName: " + item);

		BeveragesResponseModel returnValue = new BeveragesResponseModel();
		BeveragesDTO getBeveragesItem = beveragesService.getSingleItem(item);
		BeanUtils.copyProperties(getBeveragesItem, returnValue);
		Link rootLink = linkTo(methodOn(RestaurantController.class).getAllBeveragesItems(1, 10)).withRel("root");
		Link selfLink = linkTo(methodOn(RestaurantController.class).getBeveragesItemByItemName(item)).withSelfRel();
		returnValue.add(selfLink);
		returnValue.add(rootLink);
		return returnValue;
	}

	@ApiOperation(value = "Create beverage item Web Service Endpoint", notes = "This Web Service Endpoint creates and returns Beverage item.")
	@PostMapping(path = "/beverages", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public BeveragesResponseModel insertBeveragesItem(@RequestBody BeveragesRequestModel request) throws Exception {
		BeveragesResponseModel returnValue = new BeveragesResponseModel();
		BeveragesDTO requestDTO = new BeveragesDTO();
		BeanUtils.copyProperties(request, requestDTO);

		BeveragesDTO getBeveragesItem = beveragesService.setBeveragesItem(requestDTO);
		BeanUtils.copyProperties(getBeveragesItem, returnValue);
		return returnValue;
	}

	@ApiOperation(value = "Delete Beverage item Web Service Endpoint", notes = "This Web Service Endpoint deletes beverage item using their item name. For Example URL: beverages/itemName")
	@DeleteMapping(path = "/beverages/{item}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public OperationalStatusModel deleteBeveragesItem(@PathVariable String item) throws Exception {
		OperationalStatusModel returnValue = new OperationalStatusModel();
		returnValue.setOperationName(StatusResponseModel.DELETE.name());
		returnValue.setOperationStatus(StatusResponseModel.FAILURE.name());
		StatusResponseModel getStatus = beveragesService.deleteItem(item);
		if (getStatus == StatusResponseModel.SUCCESS) {
			returnValue.setOperationStatus(StatusResponseModel.SUCCESS.name());
		}
		return returnValue;
	}

	@ApiOperation(value = "Update Beverage item Web Service Endpoint", notes = "This Web Service Endpoint updates beverage item using their item name and returns its details.")
	@PutMapping(path = "/beverages", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public BeveragesResponseModel updateBeveragesItem(@RequestBody BeveragesRequestModel request) throws Exception {
		BeveragesResponseModel returnValue = new BeveragesResponseModel();
		BeveragesDTO requestDTO = new BeveragesDTO();
		BeanUtils.copyProperties(request, requestDTO);

		BeveragesDTO getBeveragesItem = beveragesService.updateBeveragesItem(requestDTO);
		BeanUtils.copyProperties(getBeveragesItem, returnValue);
		return returnValue;
	}

}
