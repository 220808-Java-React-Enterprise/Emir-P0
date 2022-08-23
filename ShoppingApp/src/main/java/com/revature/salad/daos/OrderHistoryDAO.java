package com.revature.salad.daos;

import com.revature.salad.models.Inventory;
import com.revature.salad.models.OrderHistory;
import com.revature.salad.utils.custom_exceptions.InvalidSQLException;
import com.revature.salad.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAO implements CrudeDAO<OrderHistory>{

    @Override
    public void save(OrderHistory obj) {

        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO orderhistory (order_id, salad_detail, salad_price, purchase_date, user_id, restaurant_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getOrderId());
            ps.setString(2, obj.getSaladDetail());
            ps.setString(3, obj.getSaladPrice());
            ps.setString(4, obj.getPurchaseDate());
            ps.setString(5, obj.getUserId());
            ps.setString(6, obj.getRestaurantId());
            ps.executeUpdate();

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }

    }

    @Override
    public void update(OrderHistory obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public OrderHistory getAllById(String id) {
        return null;
    }

    //@Override
    //public OrderHistory getAllById(String orderId) {
        /*
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orderhistory WHERE order_id = ?");
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new OrderHistory(rs.getString("order_id"), rs.getString("salad_detail"),
                        rs.getString("salad_price"), rs.getString("purchase_date"),
                        rs.getString("user_id"), rs.getString("restaurant_id"));
            }
        }catch (SQLException e) {
            throw new InvalidSQLException("Error occurred saving to the database");
        }*/
   //     return null;
   // }
        @Override
    public List<OrderHistory> getAll() {
        return null;
    }

    //@Override
    public List<OrderHistory> getAllHistoryById(String user_id){
        List<OrderHistory> history = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orderhistory WHERE user_id = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                OrderHistory oHisto = new OrderHistory(rs.getString("order_id"), rs.getString("salad_detail"),
                        rs.getString("salad_price"), rs.getString("purchase_date"),
                        rs.getString("user_id"), rs.getString("restaurant_id"));
                history.add(oHisto);
            }
        }catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to access the database.");
        }
        return history;
    }


}

