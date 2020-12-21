package com.lombardi.restaurant.security;

public enum UserPermission {

    // Todo: Use permissions for spring security implementation

    VIEW_CUSTOMER_PAGE("view-customer-page"),
    VIEW_EMPLOYEE_PAGE("view-employee-page");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
