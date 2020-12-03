package com.lombardi.restaurant.repository;

import com.lombardi.restaurant.bean.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Integer> {
    Optional<FoodItem> findByFoodName(String foodName);
}
