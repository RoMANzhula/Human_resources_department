<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Choose Coworkers for Project: ${project.name}</h2>

        <p>Deficit: ${count}</p>

        <form action="/project/${project.id}/choose-coworkers" method="post">
            <input type="hidden" name="projectId" value="${project.id}">
            <input type="hidden" name="count" value="${count}">

            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Select</th>
                </tr>
                </thead>
                <tbody>
                <#if availableUsers??>
                    <#list availableUsers as user>
                        <tr>
                            <td>${user.username}</td>
                            <td>
                                <input type="checkbox" name="selectedUserIds" value="${user.id}">
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Add member(s)</button>
        </form>
    </div>

</@g.page>
