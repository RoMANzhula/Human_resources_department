<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="ms-4 mt-2">
        <form method="get" action="/message-filter">
            <input type="text" name="filterByAuthor" value="${filterByAuthor?ifExists}" />
            <button type="submit">Find by author</button>
        </form>
    </div>

    <#include "blocks/messagesList.ftlh">
</@g.page>