<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Useful Links</h2>
    </div>


    <div class="container mt-3">
        <div class="row">
            <#if user??>
                <#if usefulLinks??>
                    <#list usefulLinks as usefulLink>
                        <div class="col-md-3 mt-1">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="${usefulLink.link}" target="_blank">${usefulLink.name}</a>

                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <a href="/useful-links/delete/${usefulLink.id?c}"
                                       class="btn btn-danger btn-sm float-end">
                                        Delete
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </#list>
                <#else>
                    Add your first useful link.
                </#if>
            <#else>
                <p class="mt-3">You need to be logged in to view the useful links.</p>
            </#if>
        </div>
    </div>


    <div class="container text-center">
        <a href="/useful-links/add" class="btn btn-primary btn-sm mt-3">Add Useful Link</a>
    </div>

</@g.page>
