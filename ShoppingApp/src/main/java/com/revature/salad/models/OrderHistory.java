package com.revature.salad.models;

public class OrderHistory {

    private String orderId;
    private String saladDetail;
    private String saladPrice;
    private String purchaseDate;
    private String userId;
    private String restaurantId;

    // con


    public OrderHistory() {
    }

    public OrderHistory(String userId) {
        this.userId = userId;
    }

    public OrderHistory(String orderId, String saladDetail, String saladPrice, String purchaseDate, String userId, String restaurantId) {
        this.orderId = orderId;
        this.saladDetail = saladDetail;
        this.saladPrice = saladPrice;
        this.purchaseDate = purchaseDate;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    // set get

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSaladDetail() {
        return saladDetail;
    }

    public void setSaladDetail(String saladDetail) {
        this.saladDetail = saladDetail;
    }

    public String getSaladPrice() {
        return saladPrice;
    }

    public void setSaladPrice(String saladPrice) {
        this.saladPrice = saladPrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    //to string

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderId='" + orderId + '\'' +
                ", saladDetail='" + saladDetail + '\'' +
                ", saladPrice='" + saladPrice + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", userId='" + userId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
