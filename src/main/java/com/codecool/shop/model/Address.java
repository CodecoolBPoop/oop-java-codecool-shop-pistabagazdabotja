package com.codecool.shop.model;

public class Address {
    private String country;
    private String city;
    private String zip;
    private String address;

    public Address(String country, String city, String zip, String address) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return country + ", " + zip + " " + city + " " + address;
    }
}
