package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.bean.users.User;
import com.lombardi.restaurant.repository.CustomerRepository;
import com.lombardi.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer createGuest() {
        Customer guest = new Customer("Guest", null, null, null);
        System.out.println(guest.getFoodOrders());
        saveCustomer(guest);
        return guest;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customersList = new ArrayList<>();
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers){
            customersList.add(customer);
        }
        return customersList;
    }

    @Override
    public Customer findCustomerByID(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
