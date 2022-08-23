package com.revature.salad.services;

import com.revature.salad.daos.ShopInventoryDAO;
import com.revature.salad.models.ShopInventory;

import java.util.List;

public class ShopInventoryService {

    private final ShopInventoryDAO shopInventoryDAO;

    public ShopInventoryService(ShopInventoryDAO shopInventoryDAO){
        this.shopInventoryDAO = shopInventoryDAO;
    }

    public List<ShopInventory> getQuaintityById (String id){
        return shopInventoryDAO.getAllInventoryById(id);
    }


}
