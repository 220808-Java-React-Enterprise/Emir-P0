package com.revature.salad.ui;

import com.revature.salad.daos.OrderHistoryDAO;
import com.revature.salad.models.Inventory;
import com.revature.salad.models.OrderHistory;
import com.revature.salad.models.User;
import com.revature.salad.services.InventoryService;
import com.revature.salad.services.UserService;
import com.revature.salad.services.OrderHistoryService;
import com.revature.salad.utils.custom_exceptions.InvalidInventoryException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainMenu implements IMenu{

    private final User user;
    private final UserService userService;
    private final InventoryService inventoryService;
    private final OrderHistoryService orderHistoryService;

    public MainMenu(User user, UserService userService, InventoryService inventoryService, OrderHistoryService orderHistoryService) {
        this.user = user;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.orderHistoryService = orderHistoryService;
    }



    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        String inputTwo = "";

        int totalCost = 0;
        String finalOrder = "";

        //LocalDateTime dateObj = LocalDateTime.now();
        //DateTimeFormatter formatingObj = DateTimeFormatter.ofPattern("MM-DD-YYYY HH:mm:ss");
        //String currentDate = dateObj.format(formatingObj);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();

        OrderHistory receipt = new OrderHistory();

        System.out.println("Hello " + user.getUserName() + "!");

        exit: {
            while(true){
                shoppingExit: {

                    System.out.println("What would you like to do?\n");
                    System.out.println("Order Salad - \t\t\t[1]");
                    System.out.println("View Current Order - \t[2]");
                    System.out.println("View Order History - \t[3]");
                    System.out.println("Checkout - \t\t\t\t[4]");

                    System.out.println("Shutdown - \t\t\t\t[x]");

                    System.out.print("\nEnter: ");
                    input = sc.nextLine();

                    switch(input){
                        case "1":
                            //shop();
                            shopExit:{
                                while (true){
                                    System.out.println("Please choose the ingredients you want in your salad");
                                    System.out.println("Lettuce 5$ - \t\t[1]");
                                    System.out.println("Tomatoes 4$ - \t\t[2]");
                                    System.out.println("Cucumbers 3$ - \t\t[3]");
                                    System.out.println("Avocados 7$ - \t\t[4]");
                                    System.out.println("Olives 2$ - \t\t[5]");
                                    System.out.println("Celery 2$ - \t\t[6]");
                                    System.out.println("Exit - \t\t\t\t[x]");

                                    System.out.print("\nEnter: ");
                                    inputTwo = sc.nextLine();

                                    if(Objects.equals(inputTwo, "x")){
                                        break shopExit;
                                    }
                                    if(isNotNumeric(inputTwo)){
                                        System.out.println("\nInvalid input, please try again");
                                        break;
                                    }
                                    if (Integer.parseInt(inputTwo) > 6 || Integer.parseInt(inputTwo) < 1){
                                        System.out.println("\nInvalid input, please try again");
                                        break;
                                    }

                                    try{
                                        Inventory inventory = inventoryService.getProductById(inputTwo);
                                        totalCost += Integer.parseInt(inventory.getItemPrice());
                                        finalOrder += inventory.getItemName() + " ";

                                        System.out.println("Adding item...");

                                    }catch(InvalidInventoryException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        break;
                        case "2":
                            System.out.println("\nYour Salad contains:\t" + finalOrder);
                            System.out.println("And your salad will cost\t\t" + totalCost + "$\n");

                            break;
                        case "3":
                            viewHistory();

                            break;
                        case "4":
                            System.out.println("Your salad made up of: " + finalOrder + "\nTotals to: " + totalCost + "$\n\nThank you for shopping at Saladbar Valley!\n");
                            receipt = new OrderHistory(UUID.randomUUID().toString(), finalOrder, String.valueOf(totalCost), String.valueOf(date), user.getId(), user.getRestaurant());
                            orderHistoryService.register(receipt);

                            totalCost = 0;
                            finalOrder = "";

                            break shoppingExit;
                        case "x":
                            System.exit(0);
                    }
                }
            }
        }

    }
/*
    private void shop(){

        Scanner sc = new Scanner(System.in);
        String input = "";
        int totalCost = 0;
        String finalOrder = "";

        exit: {
            while(true){
                shopExit: {
                    System.out.println("Please choose the ingredients you want in your salad");
                    System.out.println("Lettuce - \t\t[1]");
                    System.out.println("Tomatoes - \t\t[2]");
                    System.out.println("Cucumbers - \t[3]");
                    System.out.println("Avocados - \t\t[4]");
                    System.out.println("Olives - \t\t[5]");
                    System.out.println("Celery - \t\t[6]");
                    System.out.println("Exit - \t\t\t[x]");

                    System.out.print("\nEnter: ");
                    input = sc.nextLine();

                    if(Objects.equals(input, "x")){
                        break exit;
                    }

                    try{
                        Inventory inventory = inventoryService.getProductById(input);
                        totalCost += Integer.parseInt(inventory.getItemPrice());
                        finalOrder += inventory.getItemName() + " ";

                        System.out.println("Adding item...");

                    }catch(InvalidInventoryException e){
                        System.out.println(e.getMessage());
                    }


                }
            }
       }

    }
    */

    private void viewHistory(){

        List<OrderHistory> orderHistory = orderHistoryService.history(user.getId());

        System.out.println("Here is your order history: \n");

        for(OrderHistory o : orderHistory){
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Order Details: " + o.getSaladDetail());
            System.out.println("Order Price: " + o.getSaladPrice() + "$");
            System.out.println("Order Date: " + o.getPurchaseDate());
            System.out.println("Order Location: " + o.getRestaurantId());
            System.out.println();

        }
    }

    public static boolean isNotNumeric(String input){
        if (input == null) {
            return true;
        }
        try {
            double d = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

}
