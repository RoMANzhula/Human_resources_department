<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container mt-5">
        <h2>Add Useful Link</h2>
        <#if user??>
            <form action="/useful-links/add" method="post">
                <div class="form-group">
                    <label for="name">Link Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="link">Link URL:</label>
                    <input type="text" class="form-control" id="link" name="link" required>
                </div>

                <div class="container text-center mt-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-primary">Add Link</button>
                </div>
            </form>
        </#if>
    </div>

</@g.page>