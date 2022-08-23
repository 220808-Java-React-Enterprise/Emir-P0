package com.revature.salad.services;

import com.revature.salad.daos.UserDAO;
import com.revature.salad.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup(){
        sut = new UserService(mockUserDao);
    }

    /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */


    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        // Arrange
        String invalidUsername = "bduong";

        // Act
        sut.isValidUserName(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        // Arrange
        String invalidUsername = "";

        // Act
        sut.isValidUserName(invalidUsername);
    }

    @Test
    public void testDuplicateUserName(){
        String username = "baowow";

        sut.isDuplicateUsername(username);
    }

    @Test(expected = InvalidUserException.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockUserDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(invalidUsername, invalidPassword);
    }



}
