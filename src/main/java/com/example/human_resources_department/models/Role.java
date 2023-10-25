package com.example.human_resources_department.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    HR_MANAGER;

    @Override
    public String getAuthority() {
        return name(); //return enum's object as String
    }
}
