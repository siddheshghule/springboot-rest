package com.digitalhotelmanagement.digitalhotelmanagement.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1073658475702364716L;

    @GeneratedValue
    @Id
    private Integer id;

    @Column(nullable = false, length = 10)
    private String city;

    @Column(nullable = false, length = 10)
    private String country;

    @Column(nullable = false, length = 100)
    private String streetName;

    @Column(nullable = false, length = 7)
    private String postalCode;

    @Column(nullable = false, length = 20)
    private String type;

    @ManyToOne
    @JoinColumn(name = "sites_id") // site-> table name of site Entity _ field name(id)
    private SiteEntity siteDetails;

    public AddressEntity(Integer id, String city, String country, String streetName, String postalCode, String type, SiteEntity siteDetails) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.type = type;
        this.siteDetails = siteDetails;
    }

    public AddressEntity(Integer id, String city, String country, String streetName, String postalCode, String type) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.type = type;
    }

    public AddressEntity() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SiteEntity getSiteDetails() {
        return siteDetails;
    }

    public void setSiteDetails(SiteEntity siteDetails) {
        this.siteDetails = siteDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(streetName, that.streetName) && Objects.equals(postalCode, that.postalCode) && Objects.equals(type, that.type) && Objects.equals(siteDetails, that.siteDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, country, streetName, postalCode, type, siteDetails);
    }
}
