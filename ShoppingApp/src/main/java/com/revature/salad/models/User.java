package com.revature.salad.models;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private boolean admin;
    private String address;
    private String state;
    private String zip;
    private String restaurant;

    // default constructor
    public User() {
    }

    // constructors


    public User(String userId, String userName, String password, boolean admin, String restaurant) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
        this.restaurant = restaurant;
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public User(String userId, String userName, String password, String email, boolean admin, String address, String state, String zip, String restaurant) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.admin = admin;
        this.restaurant = restaurant;
    }

    //setters and getters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    //to string
    @Override
    public String toString() {
        return "User{" +
                "id='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", admin=" + admin +
                ", restaurant='" + restaurant + '\'' +
                '}';
    }
}
