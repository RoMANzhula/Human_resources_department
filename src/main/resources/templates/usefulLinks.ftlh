<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Useful Links</h2>
    </div>

    <div class="container mt-3">
        <#if user??>
            <ul class="list-group">
                <#if usefulLinks??>
                <#list usefulLinks as usefulLink>
                    <li class="list-group-item">
                        <a href="${usefulLink.link}" target="_blank">${usefulLink.name}</a>

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <a href="/useful-links/delete/${usefulLink.id?c}" class="btn btn-danger btn-sm float-end">Delete</a>
                    </li>
                </#list>
                </#if>
            </ul>
            <a href="/useful-links/add" class="btn btn-primary mt-3">Add Useful Link</a>
        <#else>
            <p class="mt-3">You need to be logged in to view the useful links.</p>
        </#if>
    </div>

</@g.page>
