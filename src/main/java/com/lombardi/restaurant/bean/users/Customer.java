package com.lombardi.restaurant.bean.users;

import com.lombardi.restaurant.bean.FoodOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User{

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CustomersOrders",
            joinColumns = @JoinColumn(name = "UserID",
                    referencedColumnName = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "FoodOrderID",
                    referencedColumnName = "FoodOrderID"))
    List<FoodOrder> foodOrders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FoodOrderID")
    private FoodOrder foodOrder;

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String email, String password) {
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
