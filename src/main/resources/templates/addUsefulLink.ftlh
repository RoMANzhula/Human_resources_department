<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center mt-3">
        <h2>Add Useful Link</h2>
    </div>

    <div class="container mt-4">
        <#if user??>
            <form action="/useful-links/add" method="post" class="needs-validation mx-auto col-6" novalidate>

                <div class="row mb-3">
                    <label for="name" class="col-sm-2 col-form-label">Link Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="link" class="col-sm-2 col-form-label">Link URL:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="link" name="link" required>
                    </div>
                </div>

                <div class="container text-center mt-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-primary">Add Link</button>
                </div>
            </form>
        </#if>
    </div>

</@g.page>