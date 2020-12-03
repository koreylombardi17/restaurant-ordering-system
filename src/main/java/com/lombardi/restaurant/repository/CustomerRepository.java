package com.lombardi.restaurant.repository;

import com.lombardi.restaurant.bean.users.Customer;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerRepository extends UserRepository<Customer>{
}
