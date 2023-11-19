<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <h2>Create Team for Project: ${project.name}</h2>

    <form action="/project/${project.id}/create-team" method="post">
        <table>
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
                        <input type="number" name="rolesAndCounts[${role}]" value="0" min="0">
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <br>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Create Team</button>
    </form>

</@g.page>