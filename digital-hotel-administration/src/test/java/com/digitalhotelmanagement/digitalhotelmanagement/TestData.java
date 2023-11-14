package com.digitalhotelmanagement.digitalhotelmanagement;

import com.digitalhotelmanagement.digitalhotelmanagement.entity.AddressEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.entity.SiteEntity;
import com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO.AddressDTO;

public class TestData {

    public static AddressDTO getAddressDTO() {
        return new AddressDTO(1, "city", "country", "streetName", "postalCode", "type");
    }

    public static SiteEntity getSiteEntity() {
        return new SiteEntity(1, "location_1", "encryptedPassword");
    }

    public static AddressEntity getAddressEntity() {
        return new AddressEntity(1, "city", "country", "streetName", "postalCode", "type", getSiteEntity());
    }
}
