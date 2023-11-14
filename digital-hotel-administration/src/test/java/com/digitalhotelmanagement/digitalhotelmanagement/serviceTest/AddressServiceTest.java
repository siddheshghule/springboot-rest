package com.digitalhotelmanagement.digitalhotelmanagement.serviceTest;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AddressEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.AddressRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.repository.SiteRepository;
import com.digitalhotelmanagement.digitalhotelmanagement.service.implementation.AddressServiceImplementation;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;

    @Mock
    SiteRepository siteRepository;

    @InjectMocks
    AddressServiceImplementation addressServiceImplementation;

    @Test
    public void getAddressByStreetName() throws Exception {

        AddressDTO expectedResult = new AddressDTO(1, "city", "country", "streetName", "postalCode", "type");
        SiteEntity siteEntity = new SiteEntity(1, "location_1", "encryptedPassword");
        AddressEntity addressEntity = new AddressEntity(
                1, "city", "country", "streetName", "postalCode", "type", siteEntity);
        List<AddressEntity> addressEntities = new ArrayList<AddressEntity>();
        addressEntities.add(addressEntity);
        siteEntity.setAddress(addressEntities);

        when(siteRepository.findByLocation("location_1")).thenReturn(siteEntity);
        when(addressRepository.findByStreetName("streetName")).thenReturn(addressEntity);
        when(addressRepository.findAllBySiteDetails(siteEntity)).thenReturn(addressEntities);

        AddressDTO actualResult = addressServiceImplementation.getAddressByStreetName("streetName", "location_1");

        assertThat(expectedResult, is(samePropertyValuesAs(actualResult)));

    }
}
