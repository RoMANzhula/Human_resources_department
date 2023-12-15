<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="container text-center">
        <h3>List of all workers</h3>
    </div>

    <div class="ms-4 mt-2">
        <form method="get" action="/coworker">
            <#if usernameFilter??>
                <input type="text" name="usernameFilter" value="${usernameFilter}" />
            <#else>
                Worker name not found
            </#if>
            <button type="submit">Find by worker name</button>
        </form>
    </div>

    <div class="container text-center">
        <table class="table table-striped-columns">
            <thead>
            <tr>
                <th>Date of registration</th>
                <th>Active</th>
                <th>Worker name</th>
                <th>Profession</th>
                <#if isAdmin>
                    <th>Go to profile</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list coworkers as coworker>
                <tr>
                    <td>${coworker.getDateOfRegistration()}</td>
                    <td>${coworker.isActive()?string('yes', 'no')}</td>
                    <td>${coworker.getUsername()}</td>
                    <td><#list coworker.userRoles as role>${role}<#sep>, </#list></td>
                    <#if isAdmin || isHR_Manager>
                        <td><a href="/coworker/${coworker.id?c}">profile_page</a></td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <div>
        <button class="ms-3" type="button" onclick="window.location.href='/main'">Go to create new employee</button>
    </div>

</@g.page>