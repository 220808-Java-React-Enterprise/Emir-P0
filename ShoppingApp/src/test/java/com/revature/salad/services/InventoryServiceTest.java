package com.revature.salad.services;

import com.revature.salad.daos.InventoryDAO;
import com.revature.salad.daos.UserDAO;
import com.revature.salad.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class InventoryServiceTest {

    private InventoryService sut;
    private final InventoryDAO mockInventoryDao = mock(InventoryDAO.class);

    @Before
    public void setup(){
        sut = new InventoryService(mockInventoryDao);
    }

    @Test
    public void testInventoryOne(){
        String item = "78sadf8agsd";

        sut.getProductById(item);
    }

}
