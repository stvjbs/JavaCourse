package com.andersenlab.dao;

import com.andersenlab.EmbeddedPostgresDatabase;
import com.andersenlab.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDAOTest {
    private EmbeddedPostgresDatabase db;
    UserDAO userDAO;

    @BeforeEach
    void setUp() {
        db = new EmbeddedPostgresDatabase();
        userDAO = new UserDAO();
        userDAO.connection = db.getConnection();
    }

    @AfterEach
    void tearDown() {
        db.closeConnection();
    }

    @Test
    void saveUserTest() {
        User newUser = new User();
        newUser.setName("John Travolta");
        assertTrue(userDAO.saveUser(newUser));
    }

    @Test
    void getUserByIdNotNullTest() {
        User newUser = new User();
        newUser.setName("John Travolta");
        userDAO.saveUser(newUser);
        assertNotEquals(userDAO.getUserById(1), null);
    }

    @Test
    void getUserByIdNotFoundTest() {
        assertThrows(IllegalArgumentException.class,
                () -> userDAO.getUserById(-1));
    }

    @Test
    void deleteUserByIdSuccessTest() {
        User newUser = new User();
        newUser.setName("John Travolta");
        userDAO.saveUser(newUser);
        userDAO.deleteUserById(1);
        assertThrows(IllegalArgumentException.class, () -> userDAO.getUserById(1));
    }

    @Test
    void deleteUserByIdNotFoundTest() {
        assertThrows(IllegalArgumentException.class, () -> userDAO.deleteUserById(1));
    }
}