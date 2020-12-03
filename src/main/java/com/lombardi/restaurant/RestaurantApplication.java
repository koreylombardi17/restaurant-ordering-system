package com.lombardi.restaurant;

import com.lombardi.restaurant.bean.FoodItem;
import com.lombardi.restaurant.bean.users.Customer;
import com.lombardi.restaurant.bean.users.Employee;
import com.lombardi.restaurant.service.CustomerServiceImpl;
import com.lombardi.restaurant.service.EmployeeServiceImpl;
import com.lombardi.restaurant.service.FoodItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication implements CommandLineRunner {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private FoodItemServiceImpl foodItemService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (foodItemService.getFoodItems().isEmpty()) {
            addFoodItems();
        }
        if (customerService.getCustomers().isEmpty()) {
            addUsers();
        }
    }

    public void addUsers() {
        Customer customer1 = new Customer("Korey", "Lombardi",
                "koreylombardi@knights.ucf.edu", "password1");
        Customer customer2 = new Customer("Kyle", "Vincent",
                "kylevincent28@yahoo.com", "password2");
        Customer customer3 = new Customer("Connor", "Ryan",
                "connorryan@yahoo.com", "password3");
        Customer customer4 = new Customer("Tonya", "Frazier",
                "tonyafrazier52@comcast.net", "password4");
        Employee testEmployee = new Employee("testemployee", "testemployee",
                "testemployee@gmail.com", "testpassword", 15.00f);
        Employee employee1 = new Employee("Jamie", "Roddy",
                "jamieroddy@gmail.com", "password1", 25.00f);
        Employee employee2 = new Employee("Kenneth", "Flakes",
                "kennethflakes@comcast.net", "password", 15.00f);
        Employee employee3 = new Employee("Katie", "Johnson",
                "katiejohnson@gmail.com", "password", 20.00f);
        Employee employee4 = new Employee("Tee", "Lyuong",
                "teelyuong@yahoo.com", "password", 22.00f);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        customerService.saveCustomer(customer3);
        customerService.saveCustomer(customer4);
        employeeService.saveEmployee(testEmployee);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        employeeService.saveEmployee(employee3);
        employeeService.saveEmployee(employee4);
    }

    public void addFoodItems() {
        FoodItem foodItem1 = new FoodItem("Hawaiian Pizza", 11.99f,
                "Homemade pizza dough baked crispy, made with mozzarella cheese, ham, pineapple and bacon.");
        FoodItem foodItem2 = new FoodItem("Deluxe Pizza", 13.99f,
                "Homemade pizza dough baked crispy, made with mozzarella cheese, pepperoni, sausage, onions, " +
                        "green peppers and mushrooms.");
        FoodItem foodItem3 = new FoodItem("Meat Pizza", 13.99f,
                "Homemade pizza dough baked crispy, made with mozzarella cheese, pepperoni, sausage, beef," +
                        "ham and bacon.");
        FoodItem foodItem4 = new FoodItem("Veggie Pizza", 13.99f,
                "Homemade pizza dough baked crispy, made with mozzarella cheese, onions, mushrooms, olives," +
                        "spinach and tomatoes");
        FoodItem foodItem5 = new FoodItem("Lasagna", 12.99f,
                "Layers of noodles, smothered with beef, marinara and ricotta cheese.");
        FoodItem foodItem6 = new FoodItem("Chicken Parmesan", 13.99f,
                "Crispy fried chicken smothered in marinara sauce topped with mozzarella cheese served with spaghetti.");
        FoodItem foodItem7 = new FoodItem("Philly Cheese Steak", 10.99f,
                "Juicy steak, american cheese, peppers and onions, served on 10' hoagie.");
        FoodItem foodItem8 = new FoodItem("Italian Sub", 10.99f,
                "Ham, salami, pepperoni, and provolone cheese serves on 10' hoagie.");
        FoodItem foodItem9 = new FoodItem("Chicken Fingers", 9.99f,
                "5 crispy chicken tenders with french fries served with bbq or ranch.");
        FoodItem foodItem10 = new FoodItem("Grilled Chicken Salad", 11.99f,
                "Crispy romaine lettuce, onions. tomatoes, mushrooms and grilled chicken tossed in ranch.");
        foodItemService.addFoodItem(foodItem1);
        foodItemService.addFoodItem(foodItem2);
        foodItemService.addFoodItem(foodItem3);
        foodItemService.addFoodItem(foodItem4);
        foodItemService.addFoodItem(foodItem5);
        foodItemService.addFoodItem(foodItem6);
        foodItemService.addFoodItem(foodItem7);
        foodItemService.addFoodItem(foodItem8);
        foodItemService.addFoodItem(foodItem9);
        foodItemService.addFoodItem(foodItem10);
    }
}
