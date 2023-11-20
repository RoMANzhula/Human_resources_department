<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <#if user??>
            <h2>My Profile</h2>
        <#else>
            <h2>${user.getUsername()} Profile</h2>
        </#if>
    </div>

    <div class="ms-3">
        Username: ${user.getUsername()}<br>
        Date of registration: ${user.getDateOfRegistration()}<br>
        Specialization: <#list user.userRoles as role>${role}<#sep>, </#list><br>
        Email: ${user.getEmail()}<br>
        Member of project(s): <#list user.projects as project>${project.name}<#sep>, </#list><br>
        Phone number: ${user.getPhone()}<br>
        My skills:
    </div>

</@g.page>