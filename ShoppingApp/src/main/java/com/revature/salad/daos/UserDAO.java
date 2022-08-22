package com.revature.salad.daos;

import com.revature.salad.models.User;
import com.revature.salad.utils.custom_exceptions.InvalidSQLException;
import com.revature.salad.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudeDAO<User>{


    @Override
    public void save(User obj) {

        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (user_id, username, password, email, admin, address, state, zip, restaurant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUserName());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setBoolean(5, obj.getAdmin());
            ps.setString(6, obj.getAddress());
            ps.setString(7, obj.getState());
            ps.setString(8, obj.getZip());
            ps.setString(9, obj.getRestaurant());
            ps.executeUpdate();

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public String getUsername(String userName){

        try(Connection con = ConnectionFactory.getInstance().getConnection()){
           PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
           ps.setString(1, userName);
           ResultSet rs = ps.executeQuery();

           if(rs.next()) return rs.getString("username");

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }

        return null;
    }

    public User getUserByUsernameAndPassword(String userName, String password){

        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) return new User(rs.getString("user_id"), rs.getString("username"),
                    rs.getString("password"), rs.getString("email"),
                    rs.getBoolean("admin"), rs.getString("address"),
                    rs.getString("state"), rs.getString("zip"),
                    rs.getString("restaurant_id"));

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }
        return null;
    }

    public List<String> getAllUserNames(){
        List<String> userNames = new ArrayList<>();

        return null;
    }



}
