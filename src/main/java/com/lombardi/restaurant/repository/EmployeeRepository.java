package com.lombardi.restaurant.repository;

import com.lombardi.restaurant.bean.users.Employee;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EmployeeRepository extends UserRepository<Employee> {
}
