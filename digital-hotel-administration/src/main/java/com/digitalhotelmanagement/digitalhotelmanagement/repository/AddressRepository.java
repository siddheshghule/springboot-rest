package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AddressEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Integer>{

	
	List<AddressEntity> findAllBySiteDetails(SiteEntity siteEntity);
	AddressEntity findByStreetName(String streetName);
}
