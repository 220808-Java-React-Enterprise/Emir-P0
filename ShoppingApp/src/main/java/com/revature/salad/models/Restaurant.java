package com.revature.salad.models;

public class Restaurant {

    private String id;
    private String address;
    private String zip;
    private String state;
    private String phoneNumber;

    //default
    public Restaurant() {
    }

    public Restaurant(String id, String address, String zip, String state, String phoneNumber) {
        this.id = id;
        this.address = address;
        this.zip = zip;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    // getter setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //to string

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
