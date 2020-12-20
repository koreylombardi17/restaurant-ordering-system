package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.User;

public interface UserService{
    boolean successfulLogin(String email, String password);
    User findUserByEmail(String email);
}
