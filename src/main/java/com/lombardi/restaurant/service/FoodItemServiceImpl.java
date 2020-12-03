package com.lombardi.restaurant.service;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService{

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public List<FoodItem> getFoodItems(){
        List<FoodItem> menu = new ArrayList<>();
        Iterable<FoodItem> foodItems = foodItemRepository.findAll();
        for (FoodItem foodItem : foodItems){
            menu.add(foodItem);
        }
        return menu;
    }

    @Override
    public List<FoodItem> getFoodItemsBySearchTerm(String searchTerm) {
        List<FoodItem> menu = new ArrayList<>();
        Iterable<FoodItem> foodItems = foodItemRepository.findAll();
        for (FoodItem foodItem : foodItems){
            if (foodItem.getFoodName().equalsIgnoreCase(searchTerm)){
                menu.add(foodItem);
            }
        }
        return menu;
    }

    @Override
    public Optional<FoodItem> findByFoodName(String foodName){
        return foodItemRepository.findByFoodName(foodName);
    }

    @Override
    public void addFoodItem(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
    }
}
