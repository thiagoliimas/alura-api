package com.aluraapi.aluraapi.domain.user;

public enum UserRole {

    STUDENT ("student"),
    INSTRUCTOR ("instructor"),
    ADMIN ("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
