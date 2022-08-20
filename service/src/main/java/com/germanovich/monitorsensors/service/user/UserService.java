package com.germanovich.monitorsensors.service.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByLoginAndPassword(final String login, final String password);
}
