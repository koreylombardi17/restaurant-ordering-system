package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.Customer;

import java.util.List;

public interface CustomerService{
    List<Customer> getCustomers();
    boolean isEmailAvailable(String email);
    void saveCustomer(Customer customer);
    Customer findCustomerByID(Integer id);
    Customer createGuest();
}
