<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h2>List of Projects</h2>
        </div>

        <div class="container text-center">
            <a href="/project/create" class="btn btn-dark">Create New Project</a>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Project Id</th>
                    <th>Project name</th>
                    <th>Created on</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <#if projects??>
                    <#list projects as project>
                        <tr>
                            <td>${project.id}</td>
                            <td><a href="/project/project_info/${project.id}">${project.name}</a></td>
                            <td>${project.getDateOfRegistration()?string("dd/MM/yyyy")}</td>
                            <td>${project.active?string("In progress", "No active")}</td>
                        </tr>
                    </#list>
                <#else>
                    <p>No projects found!</p>
                </#if>
            </tbody>
        </table>
    </div>

</@g.page>