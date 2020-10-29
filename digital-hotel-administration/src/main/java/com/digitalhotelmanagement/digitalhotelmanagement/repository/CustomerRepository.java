package com.digitalhotelmanagement.digitalhotelmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.CustomerEntity;

@Repository
//public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> 
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Integer> {

	CustomerEntity findByEmail(String email);
	Optional<CustomerEntity> findById(Integer id);
}
