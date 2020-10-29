package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;

@Repository
public interface SiteRepository extends PagingAndSortingRepository<SiteEntity, Integer> {

	SiteEntity findByLocation(String location);

}
