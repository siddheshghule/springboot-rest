package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.food.BeveragesEntity;

@Repository
public interface BeveragesRepository extends PagingAndSortingRepository<BeveragesEntity,Integer>{
	
	BeveragesEntity findByItem(String itemname);
}
