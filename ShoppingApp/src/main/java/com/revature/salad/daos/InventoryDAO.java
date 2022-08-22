package com.revature.salad.daos;

import com.revature.salad.models.Inventory;
import com.revature.salad.utils.custom_exceptions.InvalidSQLException;
import com.revature.salad.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryDAO implements CrudeDAO<Inventory>{


    @Override
    public void save(Inventory obj) {

    }

    @Override
    public void update(Inventory obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Inventory getById(String itemId) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE item_id = ?");
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Inventory(rs.getString("item_id"), rs.getString("item_name"),
                        rs.getString("item_price"));
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }

        return null;
    }

    @Override
    public List<Inventory> getAll() {
        return null;
    }

    /*
    public Inventory getIngredientNameAndPrice(String itemId){

        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE item_id = ?");
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();

            String id = rs.getString("item_id");
            String name = rs.getString("item_name");
            String price = rs.getString("item_price");

            if(rs.next()) return new Inventory(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_price"));

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred saving to the database");
        }
        return null;

    }
    */

}
