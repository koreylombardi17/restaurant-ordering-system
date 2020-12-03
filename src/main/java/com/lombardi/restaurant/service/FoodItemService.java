package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.FoodItem;

import java.util.List;
import java.util.Optional;

public interface FoodItemService {
    List<FoodItem> getFoodItems();
    List<FoodItem> getFoodItemsBySearchTerm(String searchTerm);
    void addFoodItem(FoodItem foodItem);
    Optional<FoodItem> findByFoodName(String foodName);
}
