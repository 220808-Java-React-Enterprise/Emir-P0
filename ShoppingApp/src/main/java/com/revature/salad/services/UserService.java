package com.revature.salad.services;

import com.revature.salad.daos.UserDAO;
import com.revature.salad.models.User;
import com.revature.salad.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void isValidUserName(String userName){
        if(!userName.matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")) throw new
                InvalidUserException("Invalid Username! Username should be minimum of 8 character and maximum of 20 characters long");
    }

    public void isValidPassword(String password){
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new
                InvalidUserException("Invalid Password! Password should be Minimum eight characters, at least one letter and one number");
    }

    public void register(User user){
        userDAO.save(user);
    }

    public User login(String userName, String password) {
        User user = userDAO.getUserByUsernameAndPassword(userName, password);
        if (user == null) throw new InvalidUserException("\nIncorrect username or password");
        return user;
    }

    /*
    public List<String> getAllUsernames(){
        return userDAO.getAllUserNames();
    }
     */

    public void isDuplicateUsername (String userName){
        if (userDAO.getUsername(userName) != null) throw new InvalidUserException("\nUsername is already taken please");
    }

    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidUserException("\nPassword do not match");
        return true;
    }

}
