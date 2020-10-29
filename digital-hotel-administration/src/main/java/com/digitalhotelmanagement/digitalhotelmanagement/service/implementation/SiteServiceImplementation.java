package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AddressEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.AddressRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.SiteRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.SiteService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.SiteDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class SiteServiceImplementation implements SiteService {

	@Autowired
	SiteRepository siteRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AddressServiceImplementation addressServiceImplementation;

	@Override
	public SiteDTO createSite(SiteDTO site) throws Exception {

		if (siteRepository.findByLocation(site.getLocation()) != null)
			throw new Exception(ErrorMessages.LOCATION_ALREADY_EXISTS.getErrorMessage());

		if (site.getLocation().isEmpty() || site.getPassword().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		for (int i = 0; i < site.getAddress().size(); i++) {
			AddressDTO address = site.getAddress().get(i);

			// check existing street name
			AddressEntity checkEntity = addressRepository.findByStreetName(address.getStreetName());
			if (checkEntity != null) {
				if (address.getStreetName().equals(checkEntity.getStreetName()))
					throw new Exception(ErrorMessages.STREETNAME_ALREADY_EXISTS.getErrorMessage());
			}
			address.setSiteDetails(site);
			site.getAddress().set(i, address);
		}
		ModelMapper modelMapper = new ModelMapper();
		SiteEntity entity = modelMapper.map(site, SiteEntity.class);
		entity.setEncryptedPassword(bCryptPasswordEncoder.encode(site.getPassword()));
		SiteEntity createdSite = siteRepository.save(entity);

		SiteDTO returnValue = modelMapper.map(createdSite, SiteDTO.class);
		return returnValue;
	}

	@Override
	public List<SiteDTO> getSiteDetails(int page, int limit) throws Exception {
		List<SiteDTO> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<SiteEntity> pageDetails = siteRepository.findAll(pageRequest);

		List<SiteEntity> getsites = pageDetails.getContent();

		if (page < 0 || getsites.isEmpty())
			throw new Exception(ErrorMessages.PAGE_ERROR.getErrorMessage());

		for (SiteEntity siteEntity : getsites) {
			SiteDTO sitedto = new SiteDTO();
			BeanUtils.copyProperties(siteEntity, sitedto);
			returnValue.add(sitedto);
		}
		return returnValue;
	}

	@Override
	public void deleteSite(String location) throws Exception {

		SiteEntity deleteSite = siteRepository.findByLocation(location);

		if (deleteSite == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		siteRepository.delete(deleteSite);

	}

	@Override
	public SiteDTO updateSite(SiteDTO siteDTO) throws Exception {
		SiteDTO returnValue = new SiteDTO();
		if (siteDTO.getLocation().isEmpty() || siteDTO.getPassword().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		SiteEntity entity = siteRepository.findByLocation(siteDTO.getLocation());

		if (entity == null)
			throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		siteRepository.delete(entity); // to remove duplication

		for (int i = 0; i < siteDTO.getAddress().size(); i++) {
			AddressDTO address = siteDTO.getAddress().get(i);

			// check existing street name
			AddressEntity checkEntity = addressRepository.findByStreetName(address.getStreetName());
			if (checkEntity != null) {
				if (address.getStreetName().equals(checkEntity.getStreetName()))
					throw new Exception(ErrorMessages.STREETNAME_ALREADY_EXISTS.getErrorMessage());
			}
			address.setSiteDetails(siteDTO);
			siteDTO.getAddress().set(i, address);
		}
		entity = modelMapper.map(siteDTO, SiteEntity.class);

		entity.setLocation(siteDTO.getLocation());
		entity.setEncryptedPassword(bCryptPasswordEncoder.encode(siteDTO.getPassword()));

		SiteEntity updatedSite = siteRepository.save(entity);
		returnValue = modelMapper.map(updatedSite, SiteDTO.class);
		return returnValue;
	}
}
