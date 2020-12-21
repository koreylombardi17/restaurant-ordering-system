package com.lombardi.restaurant.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.lombardi.restaurant.security.UserPermission.*;

public enum UserRole {

    // Todo: Use roles for spring security implementation

    ADMIN(Sets.newHashSet(VIEW_CUSTOMER_PAGE, VIEW_EMPLOYEE_PAGE)),
    EMPLOYEE(Sets.newHashSet(VIEW_EMPLOYEE_PAGE)),
    CUSTOMER(Sets.newHashSet(VIEW_CUSTOMER_PAGE)),
    GUEST(Sets.newHashSet(VIEW_CUSTOMER_PAGE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions =
                getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
