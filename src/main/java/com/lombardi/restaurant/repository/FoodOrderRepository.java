package com.lombardi.restaurant.repository;

import com.lombardi.restaurant.bean.FoodOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface FoodOrderRepository extends CrudRepository<FoodOrder, Integer> {
}
