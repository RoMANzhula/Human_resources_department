<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="ms-3">
        <h4>User Details</h4>
        <strong>User_Id: ${coworker.id?c};</strong>
        <strong>User name: ${coworker.getUsername()}</strong>
    </div>

</@g.page>