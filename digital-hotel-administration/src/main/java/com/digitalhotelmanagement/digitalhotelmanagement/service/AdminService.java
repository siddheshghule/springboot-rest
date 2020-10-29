package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AdminDTO;

public interface AdminService extends UserDetailsService{
	List<AdminDTO> getAdmin(int page, int limit) throws Exception;
	AdminDTO createAdmin(AdminDTO admin) throws Exception;
	AdminDTO updateAdmin(AdminDTO admin) throws Exception;
	void deleteAdmin(String username) throws Exception;
	AdminDTO getByUsername(String username) throws Exception;
	//boolean checkAdminExist(String username, String email) throws Exception;
	

}
