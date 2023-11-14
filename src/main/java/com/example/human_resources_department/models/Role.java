package com.example.human_resources_department.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    CEO,
    CTO,
    CFO,
    CMO,
    COO,
    TECHNICAL_DIRECTOR,
    PROJECT_MANAGER,
    SOFTWARE_ARCHITECT,
    QA_ENGINEER,
    UI_UX_DESIGNER,
    OPERATIONS_DIRECTOR,
    DEVOPS_ENGINEER,
    NETWORK_AND_SYSTEM_ADMIN,
    TECHNICAL_SUPPORT,
    BUSINESS_DEVELOPMENT_MANAGER,
    BUSINESS_ANALYST,
    SALES_MANAGER,
    COMMUNICATIONS_AND_PR_SPECIALIST,
    INTERNET_MARKETER,
    FINANCE_ANALYST,
    ACCOUNTANT,
    FINANCIAL_MANAGER,
    HR_DIRECTOR,
    RECRUITER,
    RESEARCH_AND_DEVELOPMENT_DIRECTOR,
    RESEARCH_AND_DEVELOPMENT_SPECIALIST,
    PRODUCT_ARCHITECT,
    LAWYER,
    IT_SECURITY_SPECIALIST,
    SECURITY_DIRECTOR,
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
