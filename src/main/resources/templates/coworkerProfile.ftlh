<#import "blocks/general.ftlh" as g>

<@g.page>
<div class="container text-center">
    <h3>Profile of__ ${coworker.getUsername()}</h3>
</div>

<form action="/coworker" method="post">
    <div class="container text-center mb-2">
        <input type="text" name="isActive" value="${coworker.isActive()?string('yes', 'no')}" />
        <input type="text" name="username" value="${coworker.username}" />

        <div class="mb-2 mt-2">
                <#list roles as role>
                    <label>
                        <input type="checkbox" name="${role}"
                        ${coworker.userRoles?seq_contains(role)?string("checked", "")} />${role}<#sep>,
                    </label>
                </#list>
        </div>
        <input type="hidden" name="coworkerId" value="${coworker.id?c}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Save changed information</button>
    </div>
</form>
</@g.page>