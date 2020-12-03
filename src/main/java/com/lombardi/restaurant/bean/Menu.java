package com.lombardi.restaurant.bean;

import java.util.List;

public class Menu {

    private List<FoodItem> menu;

    public Menu(List<FoodItem> menu) {
        this.menu = menu;
    }

    public List<FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(List<FoodItem> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                '}';
    }
}
