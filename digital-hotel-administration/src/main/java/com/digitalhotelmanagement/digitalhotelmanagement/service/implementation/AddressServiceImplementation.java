package com.digitalhotelmanagement.digitalhotelmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AddressEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.AddressRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.SiteRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.AddressesService;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;
import com.digitalhotelmanagement.digitalhotelmanagement.validation.ErrorMessages;

@Service
public class AddressServiceImplementation implements AddressesService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SiteRepository siteRepository;

    @Override
    public List<AddressDTO> getAddresses(String location) throws Exception {

        List<AddressDTO> returnValue = new ArrayList<>();
        SiteEntity siteEntity = siteRepository.findByLocation(location);
        ModelMapper modelMapper = new ModelMapper();

        if (siteEntity == null) {
            throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        Iterable<AddressEntity> getAddresses = addressRepository.findAllBySiteDetails(siteEntity);

        for (AddressEntity addressEntity : getAddresses) {
            returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
        }

        return returnValue;
    }

    public AddressDTO getAddressByStreetName(String streetName, String location) throws Exception {

        AddressDTO returnValue = new AddressDTO();
        SiteEntity siteEntity = siteRepository.findByLocation(location);
        AddressEntity addressByStreetName = addressRepository.findByStreetName(streetName);

        if (siteEntity == null) {
            throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ", location: " + location);
        }
        if (addressByStreetName == null) {
            throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ", streetName: " + streetName);
        }

        Iterable<AddressEntity> getAddresses = addressRepository.findAllBySiteDetails(siteEntity);
        for (AddressEntity addressEntity : getAddresses) {
            if (addressByStreetName.equals(addressEntity)) {
                BeanUtils.copyProperties(addressEntity, returnValue);
            } else
                throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+ ", AddressEntity: " +addressByStreetName);

        }

        return returnValue;
    }

}
