package com.revature.salad;

import com.revature.salad.daos.UserDAO;
import com.revature.salad.services.UserService;
import com.revature.salad.ui.Login;
import com.revature.salad.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // dependency injection
        new Login(new UserService(new UserDAO())).start();



    }
}
