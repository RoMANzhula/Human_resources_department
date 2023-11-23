<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <#if user??>
            <h2>${coworker.getUsername()} Profile</h2>
        <#else>
            <h2>My Profile</h2>
        </#if>
    </div>

    <div class="ms-3">
        Username: ${coworker.getUsername()}<br>
        Date of registration: ${coworker.getDateOfRegistration()}<br>
        Specialization: <#list coworker.userRoles as role>${role}<#sep>, </#list><br>
        Email: ${coworker.getEmail()}<br>
        Member of project(s): <#list coworker.projects as project>${project.name}<#sep>, </#list><br>
        Phone number: ${coworker.getPhone()}<br>
        My skills:
    </div>

    <#if coworker??>
        <div class="container text-center">
            <a href="/coworker/edit">Edit My Profile</a>
        </div>
    </#if>

</@g.page>