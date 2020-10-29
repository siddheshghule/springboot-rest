package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.FoodDTO;

public interface FoodService {
	List<FoodDTO> getItems(int page, int pageLimit) throws Exception;
	FoodDTO setFoodItem(FoodDTO foodItem) throws Exception;
	FoodDTO getSingleItem(String itemname) throws Exception;
	FoodDTO updateFoodItem(FoodDTO foodItem) throws Exception;
	StatusResponseModel deleteItem(String item) throws Exception;
	
	
}
