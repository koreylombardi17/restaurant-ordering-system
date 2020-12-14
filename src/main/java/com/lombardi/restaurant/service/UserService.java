package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean successfulLogin(String email, String password);
    User findUserByEmail(String email);
}
