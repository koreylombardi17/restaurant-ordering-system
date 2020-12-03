package com.lombardi.restaurant.controller;

import com.lombardi.restaurant.bean.FoodOrder;
import com.lombardi.restaurant.service.FoodOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private FoodOrderServiceImpl foodOrderService;

    @GetMapping("/employee-home")
    public String employeeHome(Model model) {
        List<FoodOrder> foodOrders = foodOrderService.getActiveFoodOrders();
        List<String> statuses = Arrays.asList("Received Order", "Preparing Order", "Order Done", "Picked Up");
        model.addAttribute("statuses", statuses);
        model.addAttribute("foodOrders", foodOrders);
        return "employee-home";
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam(value="status") String status,
                               @RequestParam("foodOrderID") Integer foodOrderID,
                               RedirectAttributes attributes) {
        FoodOrder foodOrder = foodOrderService.getFoodOrderByID(foodOrderID);
        status = status.substring(0, status.length() - 1);
        System.out.println(status);
        foodOrderService.updateFoodOrderStatus(foodOrder, status);
        return "redirect:/employee-home";
    }
}
