package com.revature.salad.ui;

import com.revature.salad.daos.InventoryDAO;
import com.revature.salad.daos.OrderHistoryDAO;
import com.revature.salad.daos.ShopInventoryDAO;
import com.revature.salad.daos.UserDAO;
import com.revature.salad.models.ShopInventory;
import com.revature.salad.models.User;
import com.revature.salad.services.InventoryService;
import com.revature.salad.services.OrderHistoryService;
import com.revature.salad.services.ShopInventoryService;
import com.revature.salad.services.UserService;
import com.revature.salad.utils.custom_exceptions.InvalidUserException;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Login implements IMenu{

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String input = "";

        exit: {
            while (true){
                System.out.println("\nWelcome to the Salad-Bar");
                System.out.println("" +
                        "              ▄     \n" +
                        "  ▐█▄  █▀██ ▄█ █    \n" +
                        "  ▐  ▀█    █   █    \n" +
                        " ███▄  █▄▄█▀██▀ ▄██ \n" +
                        " █▀▀█████▄▄▄███▀  █ \n" +
                        " █▌              ▄▌ \n" +
                        "  ▀▀▀█▄▄▄▄▄▄▄▄█▀▀▀   ");

                System.out.println("Login! - \t\t[1]");
                System.out.println("Signup! - \t\t[2]");
                System.out.println("Close Program - [x]");

                System.out.print("\nEnter: ");
                input = sc.nextLine();

                switch (input){
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        userService.register(user);
                        new MainMenu(user, new UserService(new UserDAO()),new InventoryService(new InventoryDAO()), new OrderHistoryService(new OrderHistoryDAO())).start();
                        break;
                    case "x":
                        break exit;

                    default:
                        System.out.println("\nInvalid input, Please try again");
                        break;
                }
            }
        }
    }

    private void login(){
        String userName = "";
        String password = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit:{
            while(true){

                System.out.println("[x] to exit\nEnter in your username: \n");
                userName = sc.nextLine();

                if(Objects.equals(userName, "x")){
                    break exit;
                }

                System.out.println("[x] to exit\nEnter in your password: \n");
                password = sc.nextLine();

                if(Objects.equals(password, "x")){
                    break exit;
                }

                try{
                    User user = userService.login(userName, password);
                    if(user.getAdmin()) new AdminMenu(user, new ShopInventory(), new UserService(new UserDAO()), new ShopInventoryService(new ShopInventoryDAO())).start();
                    else new MainMenu(user, new UserService(new UserDAO()), new InventoryService(new InventoryDAO()), new OrderHistoryService(new OrderHistoryDAO())).start();

                    break exit;
                }catch(InvalidUserException e){
                    System.out.println(e.getMessage());
                }

            }
        }

    }

    private User signup(){
        User user = new User();
        Scanner sc = new Scanner(System.in);
       // UserService userService = new UserService();

        String userName = "";
        String password = "";
        String password2 = "";

        exit: {
            while(true){
                userNameExit:
                {
                   while(true){
                       System.out.println("[x] to exit\nPlease Enter a Username: ");
                       userName = sc.nextLine();

                       if(Objects.equals(userName, "x")){
                           break exit;
                       }

                       try{
                           userService.isValidUserName(userName);
                           userService.isDuplicateUsername(userName);
                           break userNameExit;
                       }catch(InvalidUserException e){
                           System.out.println(e.getMessage());
                       }
                   }
                }

                passwordExit:
                {
                    while(true){
                        System.out.println("[x] to exit\nPlease Enter a Password: ");
                        password = sc.nextLine();

                        if(Objects.equals(password, "x")){
                            break exit;
                        }

                        try{
                            userService.isValidPassword(password);

                            System.out.println("\nRe-Enter your password: ");
                            password2 = password;
                            password = sc.nextLine();

                            userService.isSamePassword(password, password2);

                            break passwordExit;
                        }catch(InvalidUserException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }

                confirmExit:
                {
                    while(true){
                        System.out.println("Is this information correct?\n[y] - Yes\n[n] - No");
                        System.out.println("Username: " + userName + "\nPassword: " + password);
                        System.out.println("Enter: ");

                        switch(sc.nextLine().toLowerCase()){
                            case "y":
                                if(password.equals("admin1234")){
                                    user = new User(UUID.randomUUID().toString(), userName, password, true, "1");
                                }else{
                                    user = new User(UUID.randomUUID().toString(), userName, password, false, "1");
                                }
                                return user;
                            case "n":
                                System.out.println("Please try again");
                                break confirmExit;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                }
            }
        }
        return user;
    }

}
