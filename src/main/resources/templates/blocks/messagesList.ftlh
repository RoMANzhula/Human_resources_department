<#include "security.ftlh">

<div class="ms-4 mt-2">
    <form method="get" action="/message-filter">
        <input type="text" name="filterByAuthor" value="${filterByAuthor?ifExists}" />
        <button type="submit">Find by author</button>
    </form>
</div>

<div class="row row-cols-1 row-cols-md-3 g-4 m-3">
    <#if messages??>
        <#list messages as message>
            <div class="card m-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <#if message.fileName??>
                            <img src="/img/${message.fileName}" class="img-fluid rounded-start" />
                        </#if>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${message.topic}</h5>
                            <p class="card-text">${message.text}</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-body-secondary">
                                <p>
                                    <a href="/coworker/coworker-page/${message.author.id?c}">${message.authorName}</a>
                                    -- "${message.getDateOfRegistration()}
                                </p>
                            </small>
                            <#if message.author.id?c == currentUserId?c>
                                <a class="col btn btn-outline-info btn-sm me-1 ms-1 mb-1"
                                   href="/coworker-messages/${message.id?c}">
                                    Edit
                                </a>
                                <a class="col btn btn-outline-danger btn-sm me-1 ms-1 mb-1"
                                   href="/del-user-messages/${message.id?c}">
                                    Delete
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        No message
    </#if>
</div>