package com.revature.salad.ui;

import com.revature.salad.models.User;
import com.revature.salad.services.UserService;

public class MainMenu implements IMenu{

    private final User user;
    private final UserService userService;

    public MainMenu (User user, UserService userService){
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("Hello " + user.getUserName() + "!");
    }
}
