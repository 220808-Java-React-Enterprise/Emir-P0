package com.revature.salad.ui;

import com.revature.salad.models.ShopInventory;
import com.revature.salad.models.User;
import com.revature.salad.services.ShopInventoryService;
import com.revature.salad.services.UserService;
import com.revature.salad.utils.custom_exceptions.InvalidInventoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements IMenu{

    private final User user;
    private final ShopInventory shopInventory;
    private final UserService userService;
    private final ShopInventoryService shopInventoryService;

    public AdminMenu(User user, ShopInventory shopInventory, UserService userService, ShopInventoryService shopInventoryService){
        this.user = user;
        this.shopInventory = shopInventory;
        this.userService = userService;
        this.shopInventoryService = shopInventoryService;
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        String inputTwo = "";

        String item = "";
        String amount = "";

        System.out.println("Welcome to the ADMIN menu " + user.getUserName());

        exit: {
            while(true){
                aMenuExit: {
                    System.out.println("What would you like to do?\n");
                    System.out.println("Restock inventory - \t\t  [1]");

                    System.out.println("Shutdown - \t\t\t\t\t\t[x]");

                    input = sc.nextLine();


                    switch(input){
                        case "1":

                            System.out.println("Please select the location to add inventory\n");
                            System.out.println("Kansas, abc road def blv, 12345" + " - \t[1]");
                            System.out.println("Enter: ");

                            inputTwo = sc.nextLine();

                            List<String> inventoryItems = new ArrayList<>();
                            inventoryItems.add("nothing");
                            inventoryItems.add("Lettuce");
                            inventoryItems.add("Tomatoes");
                            inventoryItems.add("cucumbers");
                            inventoryItems.add("avocado");
                            inventoryItems.add("olives");
                            inventoryItems.add("celery");


                         //   if(inputTwo == "1"){
                                try{
                                    List<ShopInventory> shopinventory = shopInventoryService.getQuaintityById(/*shopInventory.getRestaurantId()*/inputTwo);

                                    System.out.println("Here is the list of items: \n");

                                    for(ShopInventory s : shopinventory){
                                        System.out.println("[ " + s.getItemId() + " ]" + "Item Name: " + inventoryItems.get(Integer.parseInt(s.getItemId())));
                                        System.out.println("Item Quantity: " + s.getItemQuantity());
                                        System.out.println();
                                    }
                                }catch(InvalidInventoryException e){
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("What would you like to update?");
                                System.out.println("Enter: ");
                                item = sc.nextLine();
                                System.out.println("What would you like to change the amount to?");
                                System.out.println("Entier: ");
                                amount = sc.nextLine();

                                



                            //   }
                            break;
                        case "x":
                            System.exit(0);
                    }
                }
            }
        }

    }
}
