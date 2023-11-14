<#import "blocks/general.ftlh" as g>

<@g.page>
    <div class="container text-center">
        <h1>Coworkers by Position</h1>
    </div>

    <#if coworkers?has_content>
        <ul>
            <#list coworkers as coworker>
                <li>${coworker.username}</li>
                <li><#list coworker.userRoles as role>${role}<#sep>, </#list></li>
            </#list>
        </ul>
    </#if>
</@g.page>