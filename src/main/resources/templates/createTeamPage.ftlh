<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h3>Create Team for Project: ${project.name}</h3>
        </div>

        <form action="/project/${project.id}/create-team" method="post">
            <table class="table table-striped table-sm">
                <thead>
                    <tr>
                        <th>Role</th>
                        <th>Number of Members</th>
                    </tr>
                </thead>
                <tbody>
                    <#list availableRoles?keys as role>
                        <tr>
                            <td>${role}</td>
                            <td>
                                <input type="number" name="rolesAndCounts[${role}]" value="0" min="0" />
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Create Team</button>
        </form>
    </div>

</@g.page>