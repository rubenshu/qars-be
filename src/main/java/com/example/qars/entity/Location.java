package com.example.qars.entity;

import javax.persistence.*;

@Entity
@Table(name = "Location")
public class Location extends BaseEntity{

    @Column(name = "city")
    private String city;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "country")
    private String country;
    @Column(name = "countrycode")
    private String countrycode;

    public Location() {
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
