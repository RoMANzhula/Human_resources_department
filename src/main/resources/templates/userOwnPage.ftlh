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

    <div class="container ms-3">
        <dl class="row">
            <dt class="col-sm-3">Username:</dt>
            <dd class="col-sm-9">${coworker.getUsername()}</dd>

            <dt class="col-sm-3">Date of registration:</dt>
            <dd class="col-sm-9">${coworker.getDateOfRegistration()?string("dd/MM/yyyy")}</dd>

            <dt class="col-sm-3">Specialization:</dt>
            <dd class="col-sm-9"><#list coworker.userRoles as role>${role}<#sep>, </#list></dd>

            <dt class="col-sm-3">Email:</dt>
            <dd class="col-sm-9">${coworker.getEmail()}</dd>

            <dt class="col-sm-3">Member of project(s):</dt>
            <dd class="col-sm-9"><#list coworker.projects as project>${project.name}<#sep>, </#list></dd>

            <dt class="col-sm-3">Phone number:</dt>
            <dd class="col-sm-9">${coworker.getPhone()}</dd>

            <dt class="col-sm-3">My skills:</dt>
            <dd class="col-sm-9">
                <!-- Додайте код для відображення навичок coworker тут -->
            </dd>
        </dl>
    </div>

    <#if coworker?? && user??>
        <div class="container text-center">
            <a href="/coworker/edit" class="btn btn-primary">Edit My Profile</a>
        </div>
    </#if>

</@g.page>
