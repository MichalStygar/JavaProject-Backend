package com.DTeam.eshop.services;

import com.DTeam.eshop.entities.User;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import org.hamcrest.Matchers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.*;

public class UserServiceTest {

    final UserService userService = mock(UserService.class);

    @Test
    public void testListAll() {

        when(userService.listAll()).thenReturn(prepareMocData());

        assertEquals(userService.listAll().get(0).getEmail(), null);
        assertEquals(userService.listAll().get(2).getPassword(), "password123");
        assertThat(userService.listAll(), Matchers.hasSize(3));

        verify(userService, times(3)).listAll();

    }

    private List<User> prepareMocData() {

        List<User> users = new ArrayList<>();

        users.add(new User());
        users.add(new User());
        users.add(new User("adres@o2.pl", 32, "password123", true));

        return users;
    }

    @Test
    public void testSave() {

        when(userService.save(any(User.class))).thenReturn(new User());

        User user = userService.save(new User());
        user = userService.save(new User());

        verify(userService, times(2)).save(user);

        assertNotEquals(user.getPassword(), "elo123");
        assertEquals(user.getEmail(), null);

    }

    @Test
    public void testGet() {

        when(userService.get(any())).thenReturn(new User("test@gmail.com", 32, "hardPassword!@#", false));

        User user = userService.get(new User().getEmail());

        verify(userService, times(1)).get(any());

        assertEquals(user.getEmail(), "test@gmail.com");
        assertEquals(user.getUserId().longValue(), 32L);
        assertEquals(user.getPassword(), "hardPassword!@#");
        assertEquals(user.getEnabled(), false);

        assertNotEquals(user.getPassword(), "admin123");
    }

    @Test
    public void testDelete() {

        final User user = new User("", 1, "", true);

        userService.delete(user.getEmail());

        verify(userService, times(1)).delete("");
    }

    @Test
    public void testIsUserExist() {

        when(userService.isUserExist(any())).thenReturn(true);

        final boolean result = userService.isUserExist("");
        assertEquals(result, true);

        verify(userService, times(1)).isUserExist("");

    }

}
