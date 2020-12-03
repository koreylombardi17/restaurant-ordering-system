package com.lombardi.restaurant.controller;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.FoodOrder;
import com.lombardi.restaurant.bean.Menu;
import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.service.CustomerServiceImpl;
import com.lombardi.restaurant.service.FoodItemServiceImpl;
import com.lombardi.restaurant.service.FoodOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class RecentOrdersController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private FoodOrderServiceImpl foodOrderService;

    @Autowired
    private FoodItemServiceImpl foodItemService;

    @GetMapping("/view-recent-orders")
    public String viewOrders(Model model) {
        return "recent-orders";
    }

    @PostMapping("/view-recent-orders")
    @Transactional
    public String viewOrders(@RequestParam("userID") Integer userID,
                             RedirectAttributes attributes) {
        Customer customer = customerService.findCustomerByID(userID);
        List<FoodOrder> orders = customer.getFoodOrders();
        System.out.println(orders.toString());
        attributes.addFlashAttribute("foodOrders", orders);
        attributes.addFlashAttribute("userID", userID);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/view-recent-orders";
    }

    @GetMapping("/view-recent-order")
    public String viewRecentOrders(Model model) {
        return "recent-order";
    }

    @PostMapping("/view-recent-order")
    @Transactional
    public String viewRecentOrder(@RequestParam("foodOrderID") Integer foodOrderID,
                                  @RequestParam("userID") Integer userID,
                            RedirectAttributes attributes) {
        FoodOrder foodOrder = foodOrderService.getFoodOrderByID(foodOrderID);
        System.out.println(foodOrder.getFoodItems());
        attributes.addFlashAttribute("foodOrder", foodOrder);
        attributes.addFlashAttribute("userID", userID);
        return "redirect:/view-recent-order";
    }

    @GetMapping("/reorder")
    public String reorder(Model model) {
        List<FoodItem> menuList = foodItemService.getFoodItems();
        Menu menu = new Menu(menuList);
        model.addAttribute("menu", menu);
        return "main-menu";
    }

    @PostMapping("/reorder")
    @Transactional
    public String reorder(@RequestParam("foodOrderID") Integer foodOrderID,
                          @RequestParam("userID") Integer userID,
                                  RedirectAttributes attributes) {
        FoodOrder foodOrder = foodOrderService.getFoodOrderByID(foodOrderID);
        Customer customer = customerService.findCustomerByID(userID);
        System.out.println(foodOrder.getFoodItems());
        foodOrderService.reorder(customer, foodOrder);
        attributes.addFlashAttribute("foodOrder", foodOrder);
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/reorder";
    }

        @GetMapping("/back-to-home-page")                 //Todo: Change the name of this mapping
    public String backToHomePage(Model model) {
        List<FoodItem> menuList = foodItemService.getFoodItems();
        Menu menu = new Menu(menuList);
        model.addAttribute("menu", menu);
        return "main-menu";
    }

    @PostMapping("/back-to-home-page")                    //Todo: Change the name of this mapping
    @Transactional
    public String backToHomePage(@RequestParam("userID") Integer userID,
                          RedirectAttributes attributes) {
        Customer customer = customerService.findCustomerByID(userID);
        System.out.println(customer.getFoodOrder());
        attributes.addFlashAttribute("customer", customer);
        return "redirect:/back-to-home-page";
    }
}
