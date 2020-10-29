package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.food.BeveragesEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response.StatusResponseModel;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.BeveragesRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.BeveragesService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.BeveragesDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class BeveragesServiceImplementation implements BeveragesService {

	@Autowired
	BeveragesRepository beverageRepository;

	@Override
	public List<BeveragesDTO> getItems(int page, int pageLimit) throws Exception {
		List<BeveragesDTO> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;
		Pageable pageRequest = PageRequest.of(page, pageLimit);
		Page<BeveragesEntity> getAllBeverageItems = beverageRepository.findAll(pageRequest);
		List<BeveragesEntity> getAllItems = getAllBeverageItems.getContent();
		if (getAllItems == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		for (BeveragesEntity beverageItem : getAllItems) {
			BeveragesDTO response = new BeveragesDTO();
			BeanUtils.copyProperties(beverageItem, response);
			returnValue.add(response);
		}
		return returnValue;
	}

	@Override
	public BeveragesDTO getSingleItem(String itemName) throws Exception {
		BeveragesDTO returnValue = new BeveragesDTO();
		BeveragesEntity getItem = beverageRepository.findByItem(itemName);
		if (getItem == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(getItem, returnValue);
		return returnValue;
	}

	@Override
	public BeveragesDTO setBeveragesItem(BeveragesDTO beverage) throws Exception {

		if (beverage.getItem().isEmpty() || beverage.getAmount() == null)
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if (beverageRepository.findByItem(beverage.getItem()) != null)
			throw new Exception(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		BeveragesDTO returnValue = new BeveragesDTO();
		BeveragesEntity entity = new BeveragesEntity();

		BeanUtils.copyProperties(beverage, entity);

		BeveragesEntity setBeverages = beverageRepository.save(entity);
		BeanUtils.copyProperties(setBeverages, returnValue);

		return returnValue;
	}

	@Override
	public StatusResponseModel deleteItem(String item) throws Exception {
		StatusResponseModel returnValue = StatusResponseModel.FAILURE;

		BeveragesDTO getItem = getSingleItem(item);
		if (getItem == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeveragesEntity entity = new BeveragesEntity();
		BeanUtils.copyProperties(getItem, entity);
		beverageRepository.delete(entity);
		returnValue = StatusResponseModel.SUCCESS;
		return returnValue;

	}

	@Override
	public BeveragesDTO updateBeveragesItem(BeveragesDTO beverage) throws Exception {
		if (beverage.getItem().isEmpty() || beverage.getAmount() == null)
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if (beverageRepository.findByItem(beverage.getItem()) == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeveragesDTO returnValue = new BeveragesDTO();
		BeveragesEntity entity = new BeveragesEntity();
		BeanUtils.copyProperties(beverage, entity);
		deleteItem(entity.getItem());
		BeveragesEntity setBeverages = beverageRepository.save(entity);
		BeanUtils.copyProperties(setBeverages, returnValue);

		return returnValue;
	}

}
