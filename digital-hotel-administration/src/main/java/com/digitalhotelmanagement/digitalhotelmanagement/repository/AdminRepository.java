package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AdminEntity;

@Repository
public interface AdminRepository extends PagingAndSortingRepository<AdminEntity, Integer> {
	
	AdminEntity findByUsername(String username);
	AdminEntity findByEmail(String email);
}
