package com.revature.salad.ui;

import com.revature.salad.models.User;
import com.revature.salad.services.UserService;

public class AdminMenu implements IMenu{

    private final User user;
    private final UserService userService;

    public AdminMenu(User user, UserService userService){
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("Welcome to the ADMIN menu " + user.getUserName());
    }
}
