package com.germanovich.monitorsensors.service.user;

import com.germanovich.monitorsensors.dao.user.UserDao;
import com.hermanovich.accountingsystem.util.MessageForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByLoginAndPassword(final String login, final String password) {
        var user = userDao.getByLogin(login);
        if (user != null && isPasswordMatches(password, user.getPassword())) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        }
        throw new UsernameNotFoundException(MessageForUser.USER_IS_NOT_FOUND.get());
    }

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        var user = userDao.getByLogin(login);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        }
        throw new UsernameNotFoundException(MessageForUser.USER_IS_NOT_FOUND.get());
    }

    private Boolean isPasswordMatches(final String password, final String userPassword) {
        return new BCryptPasswordEncoder().matches(password, userPassword);
    }
}
