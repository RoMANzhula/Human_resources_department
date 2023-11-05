<#import "blocks/general.ftlh" as g>
    <#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center"><h2>${name}</h2></div>

    <#include "blocks/messagesList.ftlh" />
</@g.page>