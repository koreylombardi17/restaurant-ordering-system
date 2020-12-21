package com.lombardi.restaurant.bean.users;

import com.lombardi.restaurant.bean.FoodOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User{

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "customersOrders",
                joinColumns = @JoinColumn(name = "userID",
                    referencedColumnName = "userID"),
                inverseJoinColumns = @JoinColumn(name = "foodOrderID",
                    referencedColumnName = "foodOrderID"))
    List<FoodOrder> foodOrders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foodOrderID")
    private FoodOrder foodOrder;

    public Customer() {

    }

    public Customer(String firstName,
                    String lastName,
                    String email,
                    String password) {
        super(firstName, lastName, email, password);
        this.foodOrder = new FoodOrder("Not Submitted");
    }

    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "} " + super.toString();
    }
}
