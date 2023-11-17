<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="container text-center">
        <h2>List of Projects</h2>
    </div>

    <div class="container text-center">
        <a href="/project/create" class="btn btn-dark">Create New Project</a>
    </div>

    <#if projects??>
        <ul>
            <#list projects as project>
                <li>
                    <a href="/project/project_info/${project.id}">${project.name}</a>
                    - Created on: ${project.getDateOfRegistration()?string("dd/MM/yyyy")}
                </li>
            </#list>
        </ul>
    <#else>
        <p>No projects found!</p>
    </#if>

</@g.page>