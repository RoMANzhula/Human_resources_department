<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Team Information for Project: ${project.name}</h2>


        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>Role</th>
                    <th>Number of Members</th>
                </tr>
            </thead>

            <tbody>
                <#list project.rolesAndCounts as role,count>
                    <#if count != 0>
                        <tr>
                            <td>${role}</td>
                            <td>${count}</td>
                        </tr>
                    </#if>
                </#list>
            </tbody>
        </table>

        <p>Total Employees:
            <#assign totalEmployees = 0>
            <#list project.rolesAndCounts?values as count>
                <#assign totalEmployees = totalEmployees + count>
            </#list>
            ${totalEmployees}
        </p>
    </div>

</@g.page>