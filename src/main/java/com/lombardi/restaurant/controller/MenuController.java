package com.lombardi.restaurant.controller;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.Menu;
import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.service.CustomerServiceImpl;
import com.lombardi.restaurant.service.FoodItemServiceImpl;
import com.lombardi.restaurant.service.FoodOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private FoodItemServiceImpl foodItemService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private FoodOrderServiceImpl foodOrderService;

    @PostMapping("/search-menu")
    @Transactional
    public String searchMenu(@RequestParam("searchTerm") String searchTerm,
                             @RequestParam("userID") Integer userID,
                             RedirectAttributes attributes) {
        Customer customer = customerService.findCustomerByID(userID);
        System.out.println(customer.getFoodOrder());
        List<FoodItem> menuList = foodItemService.getFoodItemsBySearchTerm(searchTerm);
        Menu menu = new Menu(menuList);
        attributes.addFlashAttribute("menu", menu);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/main-menu";
    }

    @PostMapping("/main-menu")
    @Transactional
    public String fullMenu(@RequestParam("userID") Integer userID,
                             RedirectAttributes attributes) {
        Customer customer = customerService.findCustomerByID(userID);
        System.out.println(customer.getFoodOrder());
        List<FoodItem> menuList = foodItemService.getFoodItems();
        Menu menu = new Menu(menuList);
        attributes.addFlashAttribute("menu", menu);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/main-menu";
    }

    @PostMapping("/add-item-to-order")
    @Transactional
    public String addItemToOrder(@RequestParam("foodName") String foodName,
                                 @RequestParam("userID") Integer userID,
                                 RedirectAttributes attributes) {
        FoodItem foodItem = foodItemService.findByFoodName(foodName).get();
        Customer customer = customerService.findCustomerByID(userID);
        foodOrderService.addFoodItemToOrder(customer, foodItem);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/main-menu";
    }

    @PostMapping("/remove-item-from-order")
    @Transactional
    public String removeItemFromOrder(@RequestParam("foodName") String foodName,
                                      @RequestParam("userID") Integer userID,
                                      RedirectAttributes attributes) {
        FoodItem foodItem = foodItemService.findByFoodName(foodName).get();
        Customer customer = customerService.findCustomerByID(userID);
        foodOrderService.removeFoodItemFromOrder(customer, foodItem);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/main-menu";
    }

    @PostMapping("/submit-order")
    @Transactional
    public String submitOrder(@RequestParam("userID") Integer userID,
                              RedirectAttributes attributes) {
        Customer customer = customerService.findCustomerByID(userID);
        foodOrderService.submitOrder(customer);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/main-menu";
    }
}