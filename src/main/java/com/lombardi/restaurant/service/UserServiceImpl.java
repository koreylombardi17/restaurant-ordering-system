package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.User;
import com.lombardi.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean successfulLogin(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null){
            return user.getPassword().compareTo(password) == 0;
        }
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        return user.get();
    }
}
