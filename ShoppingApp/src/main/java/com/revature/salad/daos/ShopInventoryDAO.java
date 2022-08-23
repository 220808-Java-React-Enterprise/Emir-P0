package com.revature.salad.daos;

import com.revature.salad.models.OrderHistory;
import com.revature.salad.models.ShopInventory;
import com.revature.salad.utils.custom_exceptions.InvalidSQLException;
import com.revature.salad.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopInventoryDAO implements CrudeDAO{

    @Override
    public void save(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Object getAllById(String id) {
        return null;
    }

    public ShopInventory updateAmount(String quant, String id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE restaurant_inventory SET item_quantity = ? WHERE res_item_id = ?");
            ps.setString(1, quant);
            ps.setString(2, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return null;
            }


        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

            return null;
    }

    //@Override
    public List<ShopInventory> getAllInventoryById(String id) {
        List<ShopInventory> shopInv = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurant_inventory WHERE restaurant_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ShopInventory shoppy= new ShopInventory(rs.getString("res_item_id"), rs.getString("item_id"),
                        rs.getString("restaurant_id"), rs.getString("item_quantity"));
                shopInv.add(shoppy);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return shopInv;
    }

    @Override
    public List getAll() {
        return null;
    }



}
