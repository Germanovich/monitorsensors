package com.gemanovich.monitorsensors.service.user;

import com.germanovich.monitorsensors.dao.user.UserDao;
import com.germanovich.monitorsensors.model.user.User;
import com.germanovich.monitorsensors.service.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserDao userDao;

    @Test
    void UserServiceImpl_loadUserByLoginAndPassword() {
        String login = "test";
        String password = "111";
        User user = User.builder()
                .id(1)
                .login(login)
                .password(new BCryptPasswordEncoder().encode(password))
                .role("Viewer")
                .build();

        Mockito.doReturn(user).when(userDao).getByLogin(ArgumentMatchers.anyString());

        UserDetails userDetails = userService.loadUserByLoginAndPassword(login, password);
        Mockito.verify(userDao).getByLogin(ArgumentMatchers.anyString());

        Assertions.assertEquals(userDetails.getUsername(), user.getLogin());
        Assertions.assertEquals(userDetails.getPassword(), user.getPassword());
    }

    @Test
    void UserServiceImpl_loadUserByLoginAndPassword_whenUserNotFoundToThrowBusinessException() {
        String login = "test";
        String password = "111";

        Mockito.doReturn(null).when(userDao).getByLogin(ArgumentMatchers.anyString());

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByLoginAndPassword(login, password));
    }

    @Test
    void UserServiceImpl_loadUserByUsername() {
        String login = "test";
        User user = User.builder()
                .id(1)
                .login(login)
                .password("password")
                .role("Viewer")
                .build();

        Mockito.doReturn(user).when(userDao).getByLogin(ArgumentMatchers.anyString());

        UserDetails userDetails = userService.loadUserByUsername(login);
        Mockito.verify(userDao).getByLogin(ArgumentMatchers.anyString());

        Assertions.assertEquals(userDetails.getUsername(), user.getLogin());
    }

    @Test
    void UserServiceImpl_loadUserByUsername_whenUserNotFoundToThrowBusinessException() {
        String login = "test";

        Mockito.doReturn(null).when(userDao).getByLogin(ArgumentMatchers.anyString());

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(login));
    }
}
