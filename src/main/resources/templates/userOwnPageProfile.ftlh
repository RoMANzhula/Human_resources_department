<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Edit Profile</h2>
    </div>

    <form method="post" action="/coworker/update" class="ms-3">
        <div class="mb-3">
            <label for="username" class="form-label">Username:</label>
            <input type="text" id="username" name="username" class="form-control" value="${user.username}" readonly>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" id="email" name="email" class="form-control" value="${user.email}" readonly>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Phone number:</label>
            <input type="tel" id="phone" name="phone" class="form-control" value="${user.phone}">
        </div>


        <div class="input-group mb-3 mt-5">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if user??>${user.id?c}</#if>" />
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </div>
    </form>

</@g.page>
