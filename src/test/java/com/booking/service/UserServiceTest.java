package com.booking.service;

import com.booking.dao.UserDao;
import com.booking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User mockUser = new User();
        when(userDao.getById(userId)).thenReturn(mockUser);

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(userDao).getById(userId);
    }

    @Test
    void testGetUserByEmail() {
        String email = "test@example.com";
        User mockUser = new User();
        when(userDao.findByByEmail(email)).thenReturn(mockUser);

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(userDao).findByByEmail(email);
    }

    @Test
    void testGetUserByEmailNotFound() {
        String email = "notfound@example.com";
        when(userDao.findByByEmail(email)).thenReturn(null);

        User result = userService.getUserByEmail(email);

        assertNull(result);
        verify(userDao).findByByEmail(email);
    }

    @Test
    void testGetUsersByName() {
        String name = "John";
        int pageSize = 10;
        int pageNum = 1;
        List<User> mockUsers = List.of(new User(), new User());
        when(userDao.findByName(name)).thenReturn(mockUsers);

        List<User> result = userService.getUsersByName(name, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockUsers.size(), result.size());
        verify(userDao).findByName(name);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userDao.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDao).save(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        when(userDao.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDao).save(user);
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;

        boolean result = userService.deleteUser(userId);

        assertTrue(result);
        verify(userDao).deleteById(userId);
    }
}