package com.example.human_resources_department.dto;

import com.example.human_resources_department.models.Role;

import java.util.Map;

public class TeamCreationDto {
    private Long projectId;
    private Map<Role, Integer> rolesAndCounts;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Map<Role, Integer> getRolesAndCounts() {
        return rolesAndCounts;
    }

    public void setRolesAndCounts(Map<Role, Integer> rolesAndCounts) {
        this.rolesAndCounts = rolesAndCounts;
    }
}
