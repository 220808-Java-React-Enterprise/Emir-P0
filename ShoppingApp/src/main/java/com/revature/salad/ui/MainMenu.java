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
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

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

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();



        OrderHistory receipt = new OrderHistory();

        System.out.println("Hello " + user.getUserName() + "!");

        exit: {
            while(true){
                shoppingExit: {
                    int totalCost = 0;
                    String finalOrder = "";

                    System.out.println("What would you like to do?");
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
                                    System.out.println("Lettuce - \t\t[1]");
                                    System.out.println("Tomatoes - \t\t[2]");
                                    System.out.println("Cucumbers - \t[3]");
                                    System.out.println("Avocados - \t\t[4]");
                                    System.out.println("Olives - \t\t[5]");
                                    System.out.println("Celery - \t\t[6]");
                                    System.out.println("Exit - \t\t\t[x]");

                                    System.out.print("\nEnter: ");
                                    inputTwo = sc.nextLine();

                                    if(Objects.equals(inputTwo, "x")){
                                        break shopExit;
                                    }

                                    if (Integer.parseInt(inputTwo) > 6 || Integer.parseInt(inputTwo) < 1){
                                        System.out.println("Invalid input, please try again");
                                        break shopExit;
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

                            /*
                            while(true){
                                System.out.println(orderHistoryService.history(user.getId()));
                                break;
                            }*/

                            break;
                        case "4":
                            System.out.println("Your salad made up of: " + finalOrder + "totals to: " + totalCost + "$\nThank you for shopping at Saladbar Valley!\n");
                            receipt = new OrderHistory(UUID.randomUUID().toString(), finalOrder, String.valueOf(totalCost), String.valueOf(date), user.getId(), user.getRestaurant());
                            orderHistoryService.register(receipt);

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
}
