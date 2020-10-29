package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.food.FoodEntity;

@Repository
public interface FoodRepository extends PagingAndSortingRepository<FoodEntity,Integer>{
	
	FoodEntity findByItem(String itemname);
}
