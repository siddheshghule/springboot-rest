package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AdminEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.AdminRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.AdminService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AdminDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	AdminRepository adminRepository;

	@Override
	public AdminDTO createAdmin(AdminDTO admin) throws Exception {
		AdminDTO returnValue = new AdminDTO();
		if (adminRepository.findByUsername(admin.getUsername())!=null)
			throw new Exception(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		if (adminRepository.findByEmail(admin.getEmail()) != null)
			throw new Exception(ErrorMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());
		AdminEntity entity = new AdminEntity();
		entity.setAdminid(admin.getAdminid());
		entity.setEmail(admin.getEmail());
		entity.setFirstname(admin.getFirstname());
		entity.setLastname(admin.getLastname());
		entity.setUsername(admin.getUsername());
		entity.setEncryptedPassword(bCryptPasswordEncoder.encode(admin.getPassword()));

		AdminEntity adminEntity = adminRepository.save(entity);
		BeanUtils.copyProperties(adminEntity, returnValue);

		return returnValue;
	}

	@Override
	public AdminDTO updateAdmin(AdminDTO admin) throws Exception {

		AdminDTO returnValue = new AdminDTO();

		AdminEntity getEntity = adminRepository.findByUsername(admin.getUsername());
		if (getEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		getEntity.setUsername(admin.getUsername());
		getEntity.setEmail(admin.getEmail());
		getEntity.setFirstname(admin.getFirstname());
		getEntity.setLastname(admin.getLastname());
		getEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		// to have unique email
		deleteAdmin(admin.getUsername());
		if (adminRepository.findByEmail(admin.getEmail()) != null)
			throw new Exception(ErrorMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());

		AdminEntity adminEntity = adminRepository.save(getEntity);
		BeanUtils.copyProperties(adminEntity, returnValue);
		return returnValue;
	}

	@Override
	public void deleteAdmin(String username) throws Exception {
		AdminEntity getEntity = adminRepository.findByUsername(username);
		if (getEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		adminRepository.delete(getEntity);

	}

	@Override
	public AdminDTO getByUsername(String username) throws Exception {
		AdminDTO returnValue = new AdminDTO();
		AdminEntity adminEntity = adminRepository.findByUsername(username);
		if (adminEntity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(adminEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<AdminDTO> getAdmin(int page, int limit) throws Exception {
		List<AdminDTO> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<AdminEntity> adminPage = adminRepository.findAll(pageRequest);

		List<AdminEntity> adminEntities = adminPage.getContent();
		if (adminEntities.isEmpty())
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		for (AdminEntity adminEntity : adminEntities) {
			AdminDTO adminDTO = new AdminDTO();
			BeanUtils.copyProperties(adminEntity, adminDTO);
			returnValue.add(adminDTO);
		}
		return returnValue;
	}

//	public boolean checkAdminExist(String username, String email) {
//		if (adminRepository.findByUsername(username) != null || adminRepository.findByEmail(email) != null)
//			return true;
//		// else
//		return false;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			return (UserDetails) getByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
