<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h2>My project(s)</h2>
        </div>

        <table class="table table-striped">
        <thead>
            <tr>
                <th>Link to info about project(s)</th>
                <th>Date of create</th>
            </tr>
        </thead>
            <tbody>
                <#if projects??>
                        <#list projects as project>
                            <tr>
                                <td><a href="/project/project_info/${project.id}">${project.name}</a></td>

                                <td>${project.getDateOfRegistration()?string("dd/MM/yyyy")}</td>
                            </tr>
                        </#list>
                <#else>
                    <p>No projects found!</p>
                </#if>
            </tbody>
        </table>
    </div>

</@g.page>