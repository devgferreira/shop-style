package com.gabriel.ferreira.ms_customer.domain.enums;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");
    private String role;

    UserRole(String role){
        this.role = role;
    }
}
