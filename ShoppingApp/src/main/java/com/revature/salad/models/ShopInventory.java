package com.revature.salad.models;

public class ShopInventory {

    private String resItemId;
    private String itemId;
    private String restaurantId;
    private String itemQuantity;

    //con

    public ShopInventory() {
    }

    public ShopInventory(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public ShopInventory(String resItemId, String itemId, String restaurantId, String itemQuantity) {
        this.resItemId = resItemId;
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.itemQuantity = itemQuantity;
    }

    //set get

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getResItemId() {
        return resItemId;
    }

    public void setResItemId(String resItemId) {
        this.resItemId = resItemId;
    }
    //to string


    @Override
    public String toString() {
        return "ShopInventory{" +
                "resItemId='" + resItemId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + restaurantId + '\'' +
                ", itemPrice='" + itemQuantity + '\'' +
                '}';
    }
}
