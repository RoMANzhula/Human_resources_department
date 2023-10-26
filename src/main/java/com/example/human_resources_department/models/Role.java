package com.example.human_resources_department.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    HR_MANAGER,
    JAVA_DEVELOPER,
    JAVASCRIPT_DEVELOPER,
    TEAM_LEAD_JAVA,
    TEAM_LEAD_JAVASCRIPT;

    @Override
    public String getAuthority() {
        return name(); //return enum's object as String
    }
}
