package com.revature.salad.services;

import com.revature.salad.daos.InventoryDAO;
import com.revature.salad.models.Inventory;

public class InventoryService {

    private final InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO){
        this.inventoryDAO = inventoryDAO;
    }

    public Inventory getProductById (String itemId){
        return inventoryDAO.getAllById(itemId);
    }

    /*
    public Inventory shop(String itemId){
        Inventory inventory = InventoryDAO.getIngredientNameAndPrice(itemId);
        if (inventory == null) throw new InvalidInventoryException("\nIncorrect username or password");
        return inventory;
    }
    */


}
