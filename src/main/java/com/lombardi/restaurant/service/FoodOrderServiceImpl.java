package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.FoodOrder;
import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.repository.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Override
    public void addEmptyFoodOrder(Customer customer) {
        FoodOrder foodOrder = new FoodOrder("Not Submitted");
        customer.setFoodOrder(foodOrder);
        foodOrder.setCustomer(customer);
    }

    @Override
    public void addFoodItemToOrder(Customer customer, FoodItem foodItem){
        FoodOrder foodOrder = customer.getFoodOrder();
        foodOrder.getFoodItems().add(foodItem);
        foodOrder.setTotal(foodItem.getPrice() + foodOrder.getTotal());
        foodOrder.setTax(foodOrder.getTotal() * .065f);
        foodOrder.setSubtotal(foodOrder.getTotal() + foodOrder.getTax());
        foodOrder.setCustomer(customer);
    }

    @Override
    public void removeFoodItemFromOrder(Customer customer, FoodItem foodItem){
        FoodOrder foodOrder = customer.getFoodOrder();
        foodOrder.getFoodItems().remove(foodItem);
        foodOrder.setTotal(foodOrder.getTotal() - foodItem.getPrice());
        foodOrder.setTax(foodOrder.getTotal() * .065f);
        foodOrder.setSubtotal(foodOrder.getTotal() + foodOrder.getTax());
        foodOrder.setCustomer(customer);
    }

    @Override
    public void submitOrder(Customer customer) {
        FoodOrder foodOrder = customer.getFoodOrder();
        foodOrder.setStatus("Received Order");
        customer.getFoodOrders().add(foodOrder);
        addEmptyFoodOrder(customer);
    }

    @Override
    public void reorder(Customer customer, FoodOrder foodOrder) {
        List<FoodItem> foodItems = foodOrder.getFoodItems();
        List<FoodItem> newFoodItems = new ArrayList<>(foodItems);
        Float total = foodOrder.getTotal();
        Float tax = foodOrder.getTax();
        Float subTotal = foodOrder.getSubtotal();
        FoodOrder newFoodOrder = new FoodOrder("Received Order");
        newFoodOrder.setFoodItems(newFoodItems);
        newFoodOrder.setTotal(total);
        newFoodOrder.setTax(tax);
        newFoodOrder.setSubtotal(subTotal);
        newFoodOrder.setCustomer(customer);
        customer.setFoodOrder(newFoodOrder);
        submitOrder(customer);
    }

    @Override
    public FoodOrder getFoodOrderByID(Integer foodOrderID) {
        return foodOrderRepository.findById(foodOrderID).get();
    }

    @Override
    public List<FoodOrder> getActiveFoodOrders() {
        List<FoodOrder> foodOrders = new ArrayList<>();
        for (FoodOrder foodOrder : foodOrderRepository.findAll()){
            if(foodOrder.getStatus().compareTo("Received Order") == 0 ||
                    foodOrder.getStatus().compareTo("Preparing Order") == 0 ||
                    foodOrder.getStatus().compareTo("Order Done") == 0){
                foodOrders.add(foodOrder);
            }
        }
        return foodOrders;
    }

    @Override
    public void updateFoodOrderStatus(FoodOrder foodOrder, String status) {
        foodOrder.setStatus(status);
        foodOrderRepository.save(foodOrder);
    }
}
