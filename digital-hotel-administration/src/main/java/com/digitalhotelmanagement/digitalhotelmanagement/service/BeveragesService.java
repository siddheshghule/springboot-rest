package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.BeveragesDTO;

public interface BeveragesService {
	
	List<BeveragesDTO> getItems(int page, int pageLimit) throws Exception;
	BeveragesDTO setBeveragesItem(BeveragesDTO beverage) throws Exception;
	BeveragesDTO getSingleItem(String itemName) throws Exception;
	StatusResponseModel deleteItem(String item) throws Exception;
	BeveragesDTO updateBeveragesItem(BeveragesDTO requestDTO) throws Exception;
	
	
}
