package com.lombardi.restaurant.bean;

import com.lombardi.restaurant.bean.users.Customer;

import javax.persistence.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodOrderID;
    private Float total;
    private Float tax;
    private Float subtotal;
    private LocalDate orderDate;
    private String status;

    @OneToOne(mappedBy = "foodOrder")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ordersFoodItems",
            joinColumns = @JoinColumn(name = "foodOrderID",
                    referencedColumnName = "foodOrderID"),
            inverseJoinColumns = @JoinColumn(name = "foodItemID",
                    referencedColumnName = "foodItemID"))
    @OrderColumn
    private List<FoodItem> foodItems = new ArrayList<>();

    @Transient
    private String formattedSubtotal;
    @Transient
    private String formattedTax;
    @Transient
    private String formattedTotal;
    @Transient
    private NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public FoodOrder() {

    }

    public FoodOrder(String status) {
        this.status = status;
        this.total = 0f;
        this.subtotal = 0f;
        this.tax = 0f;
        this.orderDate = LocalDate.now();
        this.formattedTotal = "0.00";
        this.formattedTax = "0.00";
        this.formattedSubtotal = "0.00";
    }

    public Integer getFoodOrderID() {
        return foodOrderID;
    }

    public void setFoodOrderID(Integer foodOrderID) {
        this.foodOrderID = foodOrderID;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getFormattedSubtotal() {
        return formatter.format(subtotal);
    }

    public String getFormattedTax() {
        return formatter.format(tax);
    }

    public String getFormattedTotal() {
        return formatter.format(total);
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "foodOrderID=" + foodOrderID +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", foodItems=" + foodItems +
                '}';
    }
}
