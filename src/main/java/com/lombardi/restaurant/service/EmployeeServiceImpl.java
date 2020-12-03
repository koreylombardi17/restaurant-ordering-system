package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.users.Employee;
import com.lombardi.restaurant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
