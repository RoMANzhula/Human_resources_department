<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="ms-3">
        <h4>User Details</h4>
        <strong>User name: ${coworker.getUsername()}; </strong>
        <strong>User_Id: ${coworker.id?c}; </strong>
        <strong>Profession: <#list coworker.userRoles as role>${role}<#sep>, </#list>; </strong>
    </div>
    <div class="container text-center">
        <h5>List of messages</h5>
    </div>

    <#include "blocks/messagesList.ftlh" />

</@g.page>