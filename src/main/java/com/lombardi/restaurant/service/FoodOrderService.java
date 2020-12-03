package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.FoodOrder;
import com.lombardi.restaurant.bean.users.Customer;

import java.util.List;

public interface FoodOrderService {
    void addEmptyFoodOrder(Customer customer);
    void submitOrder(Customer customer);
    void addFoodItemToOrder(Customer customer, FoodItem foodItem);
    void removeFoodItemFromOrder(Customer customer, FoodItem foodItem);
    FoodOrder getFoodOrderByID(Integer foodOrderID);
    void reorder(Customer customer, FoodOrder foodOrder);
    List<FoodOrder> getActiveFoodOrders();
    void updateFoodOrderStatus(FoodOrder foodOrder, String status);
}
