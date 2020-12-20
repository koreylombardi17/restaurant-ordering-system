package com.lombardi.restaurant.bean.users;

import javax.persistence.Entity;

@Entity
public class Employee extends User{

    private Float payRate;

    public Employee() {
    }

    public Employee(String firstName,
                    String lastName,
                    String email,
                    String password,
                    Float payRate) {
        super(firstName, lastName, email, password);
        this.payRate = payRate;
    }

    public Float getPayRate() {
        return payRate;
    }

    public void setPayRate(Float payRate) {
        this.payRate = payRate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "payRate=" + payRate +
                "} " + super.toString();
    }
}
