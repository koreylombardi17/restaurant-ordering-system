package com.lombardi.restaurant.repository;

import com.lombardi.restaurant.bean.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository<T extends User> extends CrudRepository<T, Integer> {
    Optional<User> findByEmail(String email);
}
