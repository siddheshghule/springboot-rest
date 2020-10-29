package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.food.FoodEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.FoodRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.FoodService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.FoodDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class FoodServiceImplementation implements FoodService {

	@Autowired
	FoodRepository foodRepository;

	@Override
	public List<FoodDTO> getItems(int page, int pageLimit) throws Exception {
		List<FoodDTO> returnValue = new ArrayList<FoodDTO>();
		if (page > 0)
			page = page - 1;
		Pageable pageRequest = PageRequest.of(page, pageLimit);
		Page<FoodEntity> getAllFoodItems = foodRepository.findAll(pageRequest);
		List<FoodEntity> getAllItems = getAllFoodItems.getContent();
		if (getAllItems.isEmpty())
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		for (FoodEntity foodItem : getAllItems) {
			FoodDTO response = new FoodDTO();
			BeanUtils.copyProperties(foodItem, response);
			returnValue.add(response);
		}
		return returnValue;
	}

	public FoodDTO getSingleItem(String itemname) throws Exception {
		FoodDTO returnValue = new FoodDTO();
		FoodEntity getItem = foodRepository.findByItem(itemname);
		if (getItem == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(getItem, returnValue);
		return returnValue;
	}

	@Override
	public FoodDTO setFoodItem(FoodDTO foodItem) throws Exception {
		if (foodItem.getItem().isEmpty() || foodItem.getAmount() == null)
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		FoodEntity checkItem = foodRepository.findByItem(foodItem.getItem());
		if (checkItem != null)
			throw new Exception(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		FoodDTO returnValue = new FoodDTO();
		FoodEntity entity = new FoodEntity();
		BeanUtils.copyProperties(foodItem, entity);
		FoodEntity setItem = foodRepository.save(entity);
		BeanUtils.copyProperties(setItem, returnValue);
		return returnValue;
	}

	@Override
	public StatusResponseModel deleteItem(String item) throws Exception {

		StatusResponseModel returnValue = StatusResponseModel.FAILURE;

		FoodDTO getItem = getSingleItem(item);
		if (getItem == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		FoodEntity entity = new FoodEntity();
		BeanUtils.copyProperties(getItem, entity);
		foodRepository.delete(entity);
		returnValue = StatusResponseModel.SUCCESS;
		return returnValue;
	}

	@Override
	public FoodDTO updateFoodItem(FoodDTO foodItem) throws Exception {
		if (foodItem.getItem().isEmpty() || foodItem.getAmount() == null)
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if (getSingleItem(foodItem.getItem()) == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		//FoodDTO item = foodRepository.findByItem(foodItem.getItem());
		FoodDTO returnValue = new FoodDTO();
		FoodEntity entity = foodRepository.findByItem(foodItem.getItem());
		entity.setAmount(foodItem.getAmount());
		entity.setItem(foodItem.getItem());
		deleteItem(entity.getItem());
		FoodEntity setItem = foodRepository.save(entity);
		BeanUtils.copyProperties(setItem, returnValue);
		return returnValue;
	}
}
