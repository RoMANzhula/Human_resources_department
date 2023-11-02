<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="card-columns" id="message-list">
    <#list messages as message>
        <div class="card my-3" data-id="${message.id}">
            <#if message.fileName??>
                <img src="/img/${message.fileName}" class="card-img-top" />
            </#if>
            <div class="m-2">
                <span>--${message.topic}--</span><br/>
                <i>${message.text}</i>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" href="/user-messages/${message.author.id}">${message.authorName}</a>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</@g.page>