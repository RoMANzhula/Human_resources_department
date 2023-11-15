<#import "blocks/general.ftlh" as g>
    <#include "blocks/security.ftlh">

<@g.page>
    <div class="container text-center">
        <h1>Coworkers by Position</h1>
    </div>

    <#if coworkers?has_content>
        <ul>
            <#list coworkers as coworker>
                <li>
                    ${coworker.username} --
                    <#list coworker.userRoles as role>${role}<#sep>, </#list> --
                    <#if user??>
                        <a href="/employee/${employee.id?c}">profile_page</a>
                    </#if>
                </li>
            </#list>
        </ul>
    </#if>
</@g.page>