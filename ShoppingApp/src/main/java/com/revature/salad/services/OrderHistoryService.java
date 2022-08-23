package com.revature.salad.services;

import com.revature.salad.daos.OrderHistoryDAO;
import com.revature.salad.models.OrderHistory;
import com.revature.salad.models.User;
import com.revature.salad.utils.custom_exceptions.InvalidSQLException;
import com.revature.salad.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class OrderHistoryService {

    private final OrderHistoryDAO orderHistoryDAO;

    public OrderHistoryService(OrderHistoryDAO orderHistoryDAO){
        this.orderHistoryDAO = orderHistoryDAO;
    }

    public void register(OrderHistory orderHistory){
        orderHistoryDAO.save(orderHistory);
    }

    public List<OrderHistory> history(String user_id){
        return orderHistoryDAO.getAllHistoryById(user_id);
    }

}
