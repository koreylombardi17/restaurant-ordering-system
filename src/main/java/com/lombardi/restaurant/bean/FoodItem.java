package com.lombardi.restaurant.bean;

import javax.persistence.*;

@Entity
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodItemID;
    private String foodName;
    private Float price;
    private String description;

    public FoodItem() {
    }

    public FoodItem(String foodName, Float price, String description) {
        this.foodName = foodName;
        this.price = price;
        this.description = description;
    }


    public Integer getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(Integer foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "foodItemID=" + foodItemID +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
