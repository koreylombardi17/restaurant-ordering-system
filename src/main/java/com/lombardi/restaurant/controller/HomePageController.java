package com.lombardi.restaurant.controller;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.FoodOrder;
import com.lombardi.restaurant.bean.Menu;
import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.bean.users.Employee;
import com.lombardi.restaurant.bean.users.User;
import com.lombardi.restaurant.service.*;
import com.lombardi.restaurant.validator.CreateCustomerErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FoodItemServiceImpl foodItemService;

    @Autowired
    private FoodOrderServiceImpl foodOrderService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/main-menu")
    public String mainMenu(Model model) {
        if (model.getAttribute("customer") == null) {
            Customer guest = customerService.createGuest();
            model.addAttribute("customer", guest);
        }
        if (model.getAttribute("menu") == null) {
            List<FoodItem> menuList = foodItemService.getFoodItems();
            Menu menu = new Menu(menuList);
            model.addAttribute("menu", menu);
        }
        return "main-menu";
    }

//    @PostMapping("/login")
//    @Transactional
//    public String login(@RequestParam("email") String email,
//                        @RequestParam("password") String password,
//                        RedirectAttributes attributes) {
//        if (userService.successfulLogin(email, password)) {
//            User user = userService.findUserByEmail(email);
//            if (user.getClass().equals(Customer.class)) {
//                Customer customer = (Customer)user;
//                // This line of code loads the customers order
//                System.out.println(customer.getFoodOrder());
//                attributes.addFlashAttribute("customer", customer);
//                return "redirect:/main-menu";
//            }else{
//                Employee employee = (Employee)user;
//                attributes.addFlashAttribute("employee", employee);
//                return "redirect:/employee-home";
//            }
//        }else{
//            String message = "Invalid email or password, please try again.";
//            attributes.addFlashAttribute("invalid", message);
//        }
//        return "redirect:/";
//    }

    @PostMapping("/login")
    @Transactional
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        RedirectAttributes attributes) {
        if (userService.successfulLogin(email, password)) {
            User user = (User) userService.loadUserByUsername(email);
            attributes.addFlashAttribute("customer", user);
                return "redirect:/main-menu";
        }else{
            String message = "Invalid email or password, please try again.";
            attributes.addFlashAttribute("invalid", message);
            System.out.println("error with login");
        }
        return "redirect:/";
    }

//    @PostMapping("/logout")
//    public String logout(){
//        return "redirect:/";
//    }

    @GetMapping("/create-customer") // Mapping for the home page
    public String home(Model model) {
        // Get customer attribute from model
        Object customerAttribute = model.asMap().get("customer");

        // On initial entry to the home page, customerAttribute does not exist.
        // If customerAttribute is null, create new beans and add to the model.
        // HTML code depends on customerAttribute to exist.
        if (customerAttribute == null) {
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMsg", new CreateCustomerErrorMsg());
        }
        return "create-customer";
    }

    @PostMapping("/create-customer")
    public String createCustomer(@Valid Customer customer, BindingResult binding,
                                 RedirectAttributes attributes) {
        // Check for errors
        if (binding.hasErrors()) {
            // Create Object to store error message details
            CreateCustomerErrorMsg errorMsg = new CreateCustomerErrorMsg();

            // Update errorMsg object
            for (FieldError fieldError : binding.getFieldErrors()) {
                if (fieldError.getField().equals("firstName")) {
                    errorMsg.setFirstNameErr(fieldError.getDefaultMessage());
                } else if (fieldError.getField().equals("lastName")) {
                    errorMsg.setLastNameErr(fieldError.getDefaultMessage());
                } else if (fieldError.getField().equals("email")) {
                    errorMsg.setEmailErr(fieldError.getDefaultMessage());
                } else if (fieldError.getField().equals("password")) {
                    errorMsg.setPasswordErr(fieldError.getDefaultMessage());
                }
            }
            // Add the Flash attributes that handle the errors by binding the data and repopulating
            // the form once the page gets redirected
            attributes.addFlashAttribute("customer", customer);
            attributes.addFlashAttribute("errorMsg", errorMsg);
            return "redirect:/create-customer";
        } else {
            // Successful account creation, add customer attribute to the model and navigate to menu
            customer.setFoodOrder(new FoodOrder("Not Submitted"));
            customerService.saveCustomer(customer);
            attributes.addFlashAttribute("customer", customer);
            return "redirect:/main-menu";
        }
    }
}
