<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="row row-cols-1 row-cols-md-3 g-4 m-3">
        <#list messages as message>
            <div class="card m-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <#if message.fileName??>
                            <img src="/img/${message.fileName}" class="img-fluid rounded-start">
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
                                    ${message.authorName} -- ${message.getDateOfRegistration()}
                                </p>
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@g.page>