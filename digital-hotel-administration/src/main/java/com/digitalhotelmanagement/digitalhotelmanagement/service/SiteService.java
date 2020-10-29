package com.digitalhotelmanagement.digitalhotelmanagement.service;

import java.util.List;

import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.SiteDTO;

public interface SiteService {
	SiteDTO createSite(SiteDTO site) throws Exception;

	List<SiteDTO> getSiteDetails(int page, int limit) throws Exception;

	void deleteSite(String location) throws Exception;

	SiteDTO updateSite(SiteDTO siteDTO) throws Exception;
}
