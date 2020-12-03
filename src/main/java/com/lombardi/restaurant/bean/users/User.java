package com.lombardi.restaurant.bean.users;

import com.lombardi.restaurant.validator.UniqueEmailConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Inheritance
@Entity
public abstract class User {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @NotBlank(message = "First name cannot be blank")
    @Size(min=2, max=30, message="First name must be greater than 2 characters.")
    @Column(name = "FirstName")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min=2, max=30, message="Last name must be greater than 2 characters.")
    @Column(name = "LastName")
    private String lastName;

    @UniqueEmailConstraint
    @NotBlank(message = "Email cannot be blank.")
    @Column(name = "Email", length=50)
    private String email;

    @Size(min = 5, max = 20, message = "Password must be between 5-20 characters.")
    @Column(name = "Password")
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}